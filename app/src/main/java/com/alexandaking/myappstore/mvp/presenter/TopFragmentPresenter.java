package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.TopFragmentView;

/**
 * Created by alexandaking on 2017/11/22.
 */

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView> {

    /**
     * 获取排行数据
     * @param activity
     */
    void getTopData(BaseActivity activity);
}
