package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.CategorySubscribeBean;
import com.alexandaking.myappstore.mvp.interactor.CategorySubscribeInteractor;
import com.alexandaking.myappstore.mvp.presenter.CategorySubscribePresenter;
import com.alexandaking.myappstore.mvp.view.view.CategorySubscribeView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategorySubscribePresenterImpl extends BasePresenterImpl<CategorySubscribeView> implements CategorySubscribePresenter {

    @Inject
    public CategorySubscribeInteractor categorySubscribeInteractor ;

    @Inject
    public CategorySubscribePresenterImpl(){

    }

    @Override
    public void getCategorySubscribeData(BaseActivity activity) {
        categorySubscribeInteractor.loadCategorySubscribeData(activity, new IGetDataDelegate<CategorySubscribeBean>() {
            @Override
            public void getDataSuccess(CategorySubscribeBean categorySubscribeBean) {
                mPresenterView.onCategorySubscribeDataSuccess(categorySubscribeBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategorySubscribeDataError(errmsg);
            }
        });
    }
}
