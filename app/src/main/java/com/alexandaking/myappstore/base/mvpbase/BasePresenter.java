package com.alexandaking.myappstore.base.mvpbase;

/**
 * Created by alexandaking on 2017/10/25.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}