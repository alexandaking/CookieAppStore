package com.alexandaking.myappstore.base;

import android.os.Handler;

import com.alexandaking.myappstore.BuildConfig;
import com.alexandaking.myappstore.di.component.AppComponent;
import com.alexandaking.myappstore.di.component.DaggerAppComponent;
import com.alexandaking.myappstore.di.module.AppModule;
//import com.alexandaking.myappstore.utils.App
import com.zhxu.library.RxRetrofitApp;
import com.zhxu.recyclerview.App;


/**
 * Created by alexandaking on 2017/10/12.
 */

public class StoreApplication extends App {

    private static int mMainThreadId;
    private static Handler mHandler;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new Handler();
        initApplicationComponent();
        RxRetrofitApp.init(this, BuildConfig.DEBUG);

    }

    private void initApplicationComponent(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getApplicationComponent(){
        return  mAppComponent;
    }

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}
