package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.CategorySubscribeView;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface CategorySubscribePresenter extends BasePresenter<CategorySubscribeView> {
    void getCategorySubscribeData(BaseActivity activity);
}
