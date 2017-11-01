package com.myapplication.myandroiddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.diyview.HorizontalListView;
import com.myapplication.myandroiddemo.diyview.HorizontalListViewAdapter;

/**
 * Created by Administrator on 2016/8/15.
 */
public class HorizontalListViewActivity extends Activity {
    HorizontalListView hListView;
    HorizontalListViewAdapter hListViewAdapter;
    TextView previewImg;
    View olderSelectView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_listview);
        initUI();
    }

    public void initUI() {
        hListView = (HorizontalListView) findViewById(R.id.horizon_listview);
        previewImg = (TextView) findViewById(R.id.image_preview);
        final String[] titles = {"怀师", "南怀瑾军校", "闭关", "南怀瑾", "南公庄严照", "怀师法相"};
        final int[] ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        hListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), titles, ids);
        hListView.setAdapter(hListViewAdapter);
        //      hListView.setOnItemSelectedListener(new OnItemSelectedListener() {
        //
        //          @Override
        //          public void onItemSelected(AdapterView<?> parent, View view,
        //                  int position, long id) {
        //              // TODO Auto-generated method stub
        //              if(olderSelected != null){
        //                  olderSelected.setSelected(false); //上一个选中的View恢复原背景
        //              }
        //              olderSelected = view;
        //              view.setSelected(true);
        //          }
        //
        //          @Override
        //          public void onNothingSelected(AdapterView<?> parent) {
        //              // TODO Auto-generated method stub
        //
        //          }
        //      });
        hListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//              if(olderSelectView == null){
//                  olderSelectView = view;
//              }else{
//                  olderSelectView.setSelected(false);
//                  olderSelectView = null;
//              }
//              olderSelectView = view;
//              view.setSelected(true);
                previewImg.setText(titles[position]);
//                hListViewAdapter.setSelectIndex(position);
//                hListViewAdapter.notifyDataSetChanged();

            }
        });

    }

}