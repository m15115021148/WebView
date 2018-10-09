package com.sensology.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private CustomWebView mWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.webView);
        mWebView.setContext(this);
        url = getIntent().getStringExtra("url");
        Log.e("chenmeng", "url -->" + url);

        mWebView.loadUrl(url);
    }

    /**
     * 改写物理按键——返回的逻辑
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == CustomWebView.REQUEST_SELECT_FILE) {
                if (mWebView.getUploadMessageAboveL() == null)
                    return;
                mWebView.getUploadMessageAboveL().onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                mWebView.setUploadMessageAboveL(null);
            }
        } else if (requestCode == CustomWebView.FILECHOOSER_RESULTCODE) {
            if (null == mWebView.getUploadMessage())
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mWebView.getUploadMessage().onReceiveValue(result);
            mWebView.setUploadMessage(null);
        } else
            Toast.makeText(getBaseContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }


}
