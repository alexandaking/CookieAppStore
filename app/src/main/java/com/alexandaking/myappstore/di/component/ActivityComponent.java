package com.alexandaking.myappstore.di.component;

import android.app.Activity;
import android.content.Context;

import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.activity.AppMoreRecommendActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategoryNecessaryActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategoryNewActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategorySubjectActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategorySubscribeActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategoryToolActivity;
import com.alexandaking.myappstore.mvp.view.activity.HomeActivity;
import com.alexandaking.myappstore.di.module.ActivityModule;
import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by alexandaking on 2017/10/24.
 */

@PerActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();


    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeActivity activity);
    void inject(AppDetailActivity activity);
    void inject(AppMoreRecommendActivity activity);
    void inject(CategoryNecessaryActivity activity);
    void inject(CategoryNewActivity activity);
    void inject(CategorySubjectActivity activity);
    void inject(CategorySubscribeActivity activity);
    void inject(CategoryToolActivity activity);
}
