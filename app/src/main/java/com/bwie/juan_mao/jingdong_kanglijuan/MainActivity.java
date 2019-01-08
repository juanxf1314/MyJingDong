package com.bwie.juan_mao.jingdong_kanglijuan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;

public class MainActivity extends BaseActivity {

    public static final int FLAG = 123;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(FLAG, 3000);
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
