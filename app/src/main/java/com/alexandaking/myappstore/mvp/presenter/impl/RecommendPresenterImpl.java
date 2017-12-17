package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.RecommendBean;
import com.alexandaking.myappstore.mvp.interactor.RecommendInteractor;
import com.alexandaking.myappstore.mvp.presenter.RecommendFragmentPresenter;
import com.alexandaking.myappstore.mvp.view.view.RecommendFragmentView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/10/25.
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter{

    @Inject
    RecommendInteractor recommendInteractor;

    @Inject
    public RecommendPresenterImpl(){

    }

    @Override
    public void getRecommendData(BaseActivity activity) {
        //网络请求获取数据
        recommendInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataFailure(errmsg);
            }
        });
    }

    @Override
    public void getMoreRecommendData(BaseActivity activity) {
        //网络请求获取数据
        recommendInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onMoreRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataFailure(errmsg);
            }
        });
    }
}
