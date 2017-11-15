package com.myapplication.myandroiddemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.myapplication.myandroiddemo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        text = (TextView) findViewById(R.id.text);
        new Thread() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://jandan.net/duan").get();
                    String title = doc.title();
                    XLog.e(title);
                    Elements duanzi = doc.select("div.text p");
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < duanzi.size(); i++) {
                        String content = duanzi.get(i).ownText();
                        XLog.e(content);
                        stringBuffer.append(content + "\n\n");
                    }
                    Message message = new Message();
                    message.what = 100;
                    Bundle bundle = new Bundle();
                    bundle.putString("data", String.valueOf(stringBuffer));
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    text.setText(msg.getData().getString("data"));
                    break;
            }
        }
    };
}
