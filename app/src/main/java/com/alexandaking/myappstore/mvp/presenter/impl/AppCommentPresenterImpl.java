package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.AppCommentBean;
import com.alexandaking.myappstore.mvp.interactor.AppCommentInteractor;
import com.alexandaking.myappstore.mvp.presenter.AppCommentFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.AppCommentFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppCommentPresenterImpl extends BasePresenterImpl<AppCommentFragmentView> implements AppCommentFragmentPresenter {

    @Inject
    AppCommentInteractor appCommentInteractor;

    @Inject
    public AppCommentPresenterImpl(){

    }

    @Override
    public void getAppCommentData(BaseActivity activity, String packageName) {
        appCommentInteractor.loadAppCommentData(activity, packageName, new IGetDataDelegate<AppCommentBean>() {
            @Override
            public void getDataSuccess(AppCommentBean appCommentBean) {
                mPresenterView.onAppCommentDataSuccess(appCommentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppCommentDataError(errmsg);
            }
        });
    }
}
