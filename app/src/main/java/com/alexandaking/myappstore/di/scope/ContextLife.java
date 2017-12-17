package com.alexandaking.myappstore.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by alexandaking on 2017/10/24.
 *
 * 限定符
 * 限定Context生命周期
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "";
}
