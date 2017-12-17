package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.AppRecommendBean;
import com.alexandaking.myappstore.mvp.interactor.AppRecommendInteractor;
import com.alexandaking.myappstore.mvp.presenter.AppCommentFragmentPresenter;
import com.alexandaking.myappstore.mvp.presenter.AppRecommendFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.AppCommentFragmentView;
import com.alexandaking.myappstore.mvp.view.view.AppRecommendFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/12.
 */

public class AppRecommendPresenterImpl extends BasePresenterImpl<AppRecommendFragmentView> implements AppRecommendFragmentPresenter{


    @Inject
    public AppRecommendInteractor appRecommendInteractor;

    @Inject
    public AppRecommendPresenterImpl(){

    }

    @Override
    public void getAppRecommendData(BaseActivity activity, String packageName) {
        appRecommendInteractor.loadAppRecommend(activity, packageName, new IGetDataDelegate<AppRecommendBean>() {
            @Override
            public void getDataSuccess(AppRecommendBean appRecommendBean) {
                mPresenterView.onAppRecommendDataSuccess(appRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppRecommendDataError(errmsg);
            }
        });
    }
}
