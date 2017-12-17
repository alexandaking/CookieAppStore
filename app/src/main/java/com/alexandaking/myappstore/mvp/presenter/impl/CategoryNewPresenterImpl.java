package com.alexandaking.myappstore.mvp.presenter.impl;

import com.alexandaking.myappstore.api.IGetDataDelegate;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenterImpl;
import com.alexandaking.myappstore.bean.CategoryNewBean;
import com.alexandaking.myappstore.mvp.interactor.CategoryNewInteractor;
import com.alexandaking.myappstore.mvp.presenter.CategoryNewPresenter;
import com.alexandaking.myappstore.mvp.view.view.CategoryNewView;

import javax.inject.Inject;

/**
 * Created by alexandaking on 2017/12/13.
 */

public class CategoryNewPresenterImpl extends BasePresenterImpl<CategoryNewView> implements CategoryNewPresenter {

    @Inject
    public CategoryNewInteractor categoryNewInteractor ;

    @Inject
    public CategoryNewPresenterImpl(){

    }

    @Override
    public void getCategoryNewData(BaseActivity activity) {
        categoryNewInteractor.loadCategoryNewData(activity, new IGetDataDelegate<CategoryNewBean>() {
            @Override
            public void getDataSuccess(CategoryNewBean categoryNewBean) {
                mPresenterView.onCategoryNewDataSuccess(categoryNewBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNewDataError(errmsg);
            }
        });
    }
}
