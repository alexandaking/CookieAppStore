package com.alexandaking.myappstore.mvp.view.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.base.BaseActivity;

import butterknife.BindView;

public class ApkManagementActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.progressImg)
    ImageView progressImg ;
    @BindView(R.id.nodata_localpkg_refresh)
    TextView nodata_localpkg_refresh ;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_apk_management);
    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatusBar();

        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("安装包管理");

        nodata_localpkg_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImg.setVisibility(View.VISIBLE);
                AnimationDrawable drawable = (AnimationDrawable) progressImg.getDrawable();
                drawable.start();
            }
        });



    }
}
