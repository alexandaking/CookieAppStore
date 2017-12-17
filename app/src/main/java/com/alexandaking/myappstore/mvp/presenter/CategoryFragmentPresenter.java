package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.CategoryFragmentView;
import com.alexandaking.myappstore.mvp.view.view.RecommendFragmentView;

/**
 * Created by alexandaking on 2017/10/25.
 */

public interface CategoryFragmentPresenter extends BasePresenter<CategoryFragmentView> {

    /**
     * 获取分类数据
     */
    void getCategoryData(BaseActivity activity);
}
