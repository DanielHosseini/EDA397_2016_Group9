package com.group9.eda397.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.group9.eda397.R;

/**
 * Created by ivar on 2016-05-09.
 */
public class WebActivity extends ToolbarActivity {
    private static final String URL = "https://github.com/DanielHosseini/EDA397_2016_Group9/compare/cbb50d7938f1...4dd3fbd37a79";
    WebView webview;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent = getIntent();
        String url = myIntent.getStringExtra("url");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        //myWebView.getSettings().setUserAgentString("Android");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);

        //webview = new WebView(this);
        //setContentView(webview);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }
}
