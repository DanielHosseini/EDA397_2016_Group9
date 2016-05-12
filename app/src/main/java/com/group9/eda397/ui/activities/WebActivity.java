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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent = getIntent();
        String url = myIntent.getStringExtra("url");
        WebView myWebView = (WebView) findViewById(R.id.webview);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }
}
