package com.myapplication.myandroiddemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected Context context;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initData();

    protected abstract void initView();

    public boolean canShow() {
        if (context != null) {
            if (context instanceof Activity) {
                Activity mActivity = (Activity) context;
                if (mActivity.isFinishing() || mActivity.isDestroyed()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected void runOnUiThread(Runnable runnable) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        }
    }
}
