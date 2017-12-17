package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.AppCommentBean;

/**
 * Created by alexandaking on 2017/12/12.
 */

public interface AppCommentFragmentView extends BaseView {
    void onAppCommentDataSuccess(AppCommentBean appCommentBean);
    void onAppCommentDataError(String msg);
}
