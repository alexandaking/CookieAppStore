package com.alexandaking.myappstore.mvp.presenter.impl;


import android.util.Log;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.AppDetailBean;
import com.alexandaking.myappstore.mvp.interactor.AppDetailInteractor;
import com.alexandaking.myappstore.mvp.presenter.AppDetailPresenter;
import com.alexandaking.myappstore.mvp.view.view.AppDetailFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/4.
 */

public class AppDetailPresenterImpl extends BasePresenterImpl<AppDetailFragmentView> implements AppDetailPresenter {

    @Inject
    public AppDetailInteractor appDetailInteractor;

    @Inject
    public AppDetailPresenterImpl(){

    }


    @Override
    public void getAppDetailData(BaseActivity activity, String packageName) {
        appDetailInteractor.loadAppDetailData(activity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
                Log.i("AppDetailPresenterImpl", appDetailBean.toString());
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppDetailDataError(errmsg);
            }
        });
    }
}
