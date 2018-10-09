package com.sensology.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private CustomWebView mWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.webView);
        url = getIntent().getStringExtra("url");
        Log.e("chenmeng","url -->"+url);

        mWebView.loadUrl(url);
        mWebView.setWebListener(new CustomWebView.OnWebViewListener() {
            @Override
            public void onProgressChanged(int progress) {

            }

            @Override
            public void onError() {
                Log.e("result","onError");
            }
        });
    }

    /**
     * 改写物理按键——返回的逻辑
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();// 返回上一页面
                return true;
            } else {
                this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
