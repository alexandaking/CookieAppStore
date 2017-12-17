package com.alexandaking.myappstore.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandaking on 2017/10/24.
 */

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
       this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity provideFragmentActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFragmentContext(){
        return mFragment.getContext();
    }
}
