package com.alexandaking.myappstore.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.widget.CustomVideoView;

import com.alexandaking.myappstore.base.BaseActivity;
import butterknife.BindView;

/**
 * Created by alexandaking on 2017/10/7.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.enter_button)
    Button btn_go;
    private CustomVideoView welcome_video;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {
        welcome_video = findViewById(R.id.welcome_videoview);
        welcome_video.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.cookie));
        welcome_video.start();
        welcome_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                welcome_video.start();
            }
        });

        btn_go.getBackground().setAlpha(50);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(welcome_video.isPlaying()){
                    welcome_video.stopPlayback();
                    welcome_video=null;
                }
                initPermission();
            }
        });
    }

    /**
     * 申请权限  SD卡的读写权限
     */
    private void initPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED){
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }else {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PermissionChecker.PERMISSION_GRANTED){
            //申请权限成功
            Toast.makeText(this,"授权SD卡权限成功",Toast.LENGTH_SHORT).show();
        }else {
            //申请权限失败
            Toast.makeText(this,"授权SD卡权限失败，可能会导致应用程序无法正常使用",Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(this,HomeActivity.class));
        SplashActivity.this.finish();
    }
}
