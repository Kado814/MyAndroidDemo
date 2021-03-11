package com.myapplication.myandroiddemo.activity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.just.agentweb.AgentWebView;
import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.MyLog;

public class WebviewActivity extends AppCompatActivity {

    private AgentWebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv=findViewById(R.id.webView);

        wv.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);//开启硬件加速

        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        wv.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        wv.getSettings().setSupportZoom(false);//是否可以缩放，默认true
        wv.getSettings().setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        wv.getSettings().setUseWideViewPort(false);//设置此属性，可任意比例缩放。大视图模式
        wv.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        wv.getSettings().setAppCacheEnabled(true);//是否使用缓存
        wv.getSettings().setDomStorageEnabled(true);//DOM Storage
        wv.loadUrl("file:////android_asset/test.html");
    }
}