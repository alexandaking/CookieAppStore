package com.alexandaking.myappstore.base.mvpbase;

/**
 * Created by alexandaking on 2017/10/25.
 *
 * 因为所有presenter都会有绑定和解绑View的操作，所以将其抽取到基类
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView;

    @Override
    public void attachView(T view) {
        this.mPresenterView = view;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null;
    }
}
