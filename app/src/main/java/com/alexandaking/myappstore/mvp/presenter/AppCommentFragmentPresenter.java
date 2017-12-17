package com.alexandaking.myappstore.mvp.presenter;

import android.support.v7.view.menu.BaseMenuPresenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.AppCommentFragmentView;

/**
 * Created by alexandaking on 2017/12/12.
 */

public interface AppCommentFragmentPresenter extends BasePresenter<AppCommentFragmentView> {
    void getAppCommentData(BaseActivity activity, String packageName);
}
