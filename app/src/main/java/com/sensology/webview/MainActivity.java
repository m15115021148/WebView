package com.sensology.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mEt;
    private String url = "https://mallsit.senhome.com.cn/discovery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt = findViewById(R.id.et);
        mEt.setText(url);
    }

    public void onTest(View view) {
        if (TextUtils.isEmpty(mEt.getText().toString())){
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra("url",mEt.getText().toString());
        startActivity(intent);
    }
}
