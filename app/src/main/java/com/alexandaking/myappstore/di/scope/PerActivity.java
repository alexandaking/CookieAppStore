package com.alexandaking.myappstore.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by alexandaking on 2017/10/24.
 * 自定义Scope 限定Activity的作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {


}
