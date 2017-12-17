package com.alexandaking.myappstore.di.component;

import android.content.Context;

import com.alexandaking.myappstore.di.module.AppModule;
import com.alexandaking.myappstore.di.scope.ContextLife;
import com.alexandaking.myappstore.di.scope.PerApp;

import dagger.Component;

/**
 * Created by alexandaking on 2017/10/24.
 *
 * 提供全局的Context对象
 */

@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();


}
