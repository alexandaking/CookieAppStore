package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.AppMoreRecommendBean;
import com.alexandaking.myappstore.mvp.interactor.AppMoreRecommendInteractor;
import com.alexandaking.myappstore.mvp.presenter.AppMoreRecommendPresenter;
import com.alexandaking.myappstore.mvp.view.view.AppMoreRecommendView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class AppMoreRecommendImpl extends BasePresenterImpl<AppMoreRecommendView> implements AppMoreRecommendPresenter {

    @Inject
    public AppMoreRecommendInteractor appMoreRecommendInteractor;

    @Inject
    public AppMoreRecommendImpl (){

    }

    @Override
    public void getAppMoreRecommendData(BaseActivity activity, String type, String packageName) {
        appMoreRecommendInteractor.loadAppMoreRecommend(activity, type, packageName, new IGetDataDelegate<AppMoreRecommendBean>() {
            @Override
            public void getDataSuccess(AppMoreRecommendBean appMoreRecommendBean) {
                mPresenterView.onAppMoreRecommendDataSuccess(appMoreRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppMoreRecommendDataError(errmsg);
            }
        });
    }
}
