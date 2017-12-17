package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.AppDetailFragmentView;

/**
 * Created by alexandaking on 2017/12/4.
 */

public interface AppDetailPresenter extends BasePresenter<AppDetailFragmentView>{
    void getAppDetailData(BaseActivity activity, String packageName);
}
