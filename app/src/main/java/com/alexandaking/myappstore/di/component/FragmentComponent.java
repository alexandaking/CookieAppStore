package com.alexandaking.myappstore.di.component;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alexandaking.myappstore.di.module.FragmentModule;
import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerFragment;
import com.alexandaking.myappstore.mvp.view.fragment.AppCommentFragment;
import com.alexandaking.myappstore.mvp.view.fragment.AppIntroductionFragment;
import com.alexandaking.myappstore.mvp.view.fragment.AppManagerFragment;
import com.alexandaking.myappstore.mvp.view.fragment.AppRecommendFragment;
import com.alexandaking.myappstore.mvp.view.fragment.CategoryFragment;
import com.alexandaking.myappstore.mvp.view.fragment.MyFragment;
import com.alexandaking.myappstore.mvp.view.fragment.RecommendFragment;
import com.alexandaking.myappstore.mvp.view.fragment.TopFragment;

import dagger.Component;

/**
 * Created by alexandaking on 2017/10/24.
 */

@PerFragment
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getFragmentContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Fragment getFragment();

    void inject(RecommendFragment fragment);
    void inject(TopFragment fragment);
    void inject(AppManagerFragment fragment);
    void inject(CategoryFragment fragment);
    void inject(MyFragment fragment);
    void inject(AppIntroductionFragment fragment);
    void inject(AppCommentFragment fragment);
    void inject(AppRecommendFragment fragment);
}
