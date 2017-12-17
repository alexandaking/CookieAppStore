package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.AppDetailBean;

/**
 * Created by alexandaking on 2017/12/3.
 */

public interface AppDetailFragmentView extends BaseView {

    void onAppDetailDataSuccess(AppDetailBean appDetailBean);
    void onAppDetailDataError(String msg);
}
