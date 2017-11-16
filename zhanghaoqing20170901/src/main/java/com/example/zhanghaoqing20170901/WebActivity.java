package com.example.zhanghaoqing20170901;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by MK on 2017/8/31.
 */
public class WebActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        WebView webView = (WebView) findViewById(R.id.webview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient());

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);



    }
}
