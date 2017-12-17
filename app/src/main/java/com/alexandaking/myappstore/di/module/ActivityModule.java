package com.alexandaking.myappstore.di.module;

import android.app.Activity;
import android.content.Context;

import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandaking on 2017/10/24.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule (Activity activity){
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mActivity;
    }
}
