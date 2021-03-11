package com.myapplication.myandroiddemo.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.diyview.PhotoView.OnViewDragListener;
import com.myapplication.myandroiddemo.diyview.PhotoView.PhotoView;
import com.myapplication.myandroiddemo.diyview.PieChartsView;
import com.myapplication.myandroiddemo.diyview.PieData;
import com.myapplication.myandroiddemo.view.ClassHourProgress;

import java.util.ArrayList;
import java.util.List;

public class DiyViewActivity extends AppCompatActivity {


    private PieChartsView pieChartsView;
    private PhotoView photoView;
    private ClassHourProgress classhourprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        final TaiJi taiJi = new TaiJi(this);
        setContentView(R.layout.activity_diy);
        pieChartsView = findViewById(R.id.pieChartsView);
        photoView = findViewById(R.id.dragImageView);
        classhourprogress = findViewById(R.id.classhourprogress);
        classhourprogress.setPercent(0.5f);
        int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000};
        List<PieData> pieDatas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PieData pieData = new PieData("name" + i, i + 20);
            pieData.setColor(mColors[i]);
            pieDatas.add(pieData);
        }
        pieChartsView.setDataList(pieDatas);

        photoView.setImageDrawable(getDrawable(R.mipmap.img01));
        photoView.setOnViewDragListener(new OnViewDragListener() {
            @Override
            public void onDrag(float dx, float dy) {
                Log.e("kkk",dx+"----"+dy);
            }
        });

        //        setContentView(taiJi);
        //        final Handler handler
        // = new Handler() {
        //            private float degrees = 0;
        //
        //            @Override
        //            public void handleMessage(Message msg) {
        //                super.handleMessage(msg);
        //                taiJi.setRotate(degrees += 5);
        //                this.sendEmptyMessageDelayed(0, 80);
        //            }
        //        };
        //
        //        handler.sendEmptyMessageDelayed(0, 20);
        //        setContentView(R.layout.activity_diyview);
    }

}
