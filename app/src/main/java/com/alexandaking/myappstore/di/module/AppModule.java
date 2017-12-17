package com.alexandaking.myappstore.di.module;

import android.content.Context;

import com.alexandaking.myappstore.base.StoreApplication;
import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandaking on 2017/10/24.
 */

@Module
public class AppModule {

    private StoreApplication storeApplication;

    public AppModule(StoreApplication storeApplication){
        this.storeApplication = storeApplication;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return storeApplication.getApplicationContext();
    }
}
