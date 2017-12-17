package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.RecommendFragmentView;

/**
 * Created by alexandaking on 2017/10/25.
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {

    /**
     * 获取数据
     */
    void getRecommendData(BaseActivity activity);

    void getMoreRecommendData(BaseActivity activity);
}
