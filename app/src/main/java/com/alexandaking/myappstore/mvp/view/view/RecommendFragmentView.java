package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.RecommendBean;

/**
 * Created by alexandaking on 2017/10/25.
 */

public interface RecommendFragmentView extends BaseView{

    void onRecommendDataSuccess(RecommendBean recommendBean);
    void onMoreRecommendDataSuccess(RecommendBean recommendBean);
    void onRecommendDataFailure(String msg);
}
