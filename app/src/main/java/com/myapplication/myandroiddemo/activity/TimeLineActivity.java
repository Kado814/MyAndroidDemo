package com.myapplication.myandroiddemo.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.diyview.TimeLineDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        recyclerView = findViewById(R.id.recyclerview);

        initData();
        initView();
    }

    private void initData() {
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();

        map1.put("title", "美国谷歌公司已发出");
        map1.put("text", "发件人:谷歌 CEO Sundar Pichai");
        map1.put("time", "13:40");
        map1.put("date", "2017.4.03");
        listItem.add(map1);

        map2.put("title", "国际顺丰已收入");
        map2.put("text", "等待中转");
        map2.put("time", "13:40");
        map2.put("date", "2017.4.03");
        listItem.add(map2);

        map3.put("title", "国际顺丰转件中");
        map3.put("text", "下一站中国");
        map3.put("time", "13:40");
        map3.put("date", "2017.4.03");
        listItem.add(map3);

        map4.put("title", "中国顺丰已收入");
        map4.put("text", "下一站广州华南理工大学");
        map4.put("time", "13:40");
        map4.put("date", "2017.4.03");
        listItem.add(map4);

        map5.put("title", "中国顺丰派件中");
        map5.put("text", "等待派件");
        map5.put("time", "13:40");
        map5.put("date", "2017.4.03");
        listItem.add(map5);

        map6.put("title", "华南理工大学已签收");
        map6.put("text", "收件人:Carson");
        map6.put("time", "13:40");
        map6.put("date", "2017.4.03");
        listItem.add(map6);
    }

    public void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //用自定义分割线类设置分割线
        recyclerView.addItemDecoration(new TimeLineDecoration(listItem));

        //为ListView绑定适配器
        myAdapter = new MyAdapter(this, listItem);
        recyclerView.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter {
        private LayoutInflater inflater;
        private ArrayList<HashMap<String, Object>> list;

        public MyAdapter(Context context, ArrayList<HashMap<String, Object>> list) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new Viewholder(inflater.inflate(R.layout.list_cell, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Viewholder viewholder = (Viewholder) holder;
            viewholder.title.setText((String) list.get(position).get("title"));
            viewholder.text.setText((String) list.get(position).get("text"));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView text;

        public Viewholder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Itemtitle);
            text = itemView.findViewById(R.id.Itemtext);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getText() {
            return text;
        }
    }
}
