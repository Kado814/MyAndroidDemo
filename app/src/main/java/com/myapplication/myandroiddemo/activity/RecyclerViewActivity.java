package com.myapplication.myandroiddemo.activity;

import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.MyToast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends MyBaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private GridLayoutManager gridLayoutManager;
    private DividerGridItemDecoration dividerGridItemDecoration;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        gridLayoutManager = new GridLayoutManager(this, 4);
        dividerGridItemDecoration = new DividerGridItemDecoration(this);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);


        initData();
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(dividerItemDecoration);//��ӷָ���
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());// ����item����
        mAdapter = new HomeAdapter();
        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                MyToast.showShort(RecyclerViewActivity.this, position + "click");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                MyToast.showShort(RecyclerViewActivity.this, position + " long click");
                mAdapter.removeData(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("aaa" + i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(dividerItemDecoration);//��ӷָ���
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_2:
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.addItemDecoration(dividerGridItemDecoration);//��ӷָ���
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_3:
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                mRecyclerView.addItemDecoration(dividerGridItemDecoration);//��ӷָ���
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        public void addData(int position) {
            mDatas.add(position, "Insert One");
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.item_recyclerview1, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
            // ��������˻ص��������õ���¼�
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
