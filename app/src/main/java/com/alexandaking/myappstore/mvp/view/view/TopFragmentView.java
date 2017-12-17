package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.TopBean;

/**
 * Created by alexandaking on 2017/11/22.
 */

public interface TopFragmentView extends BaseView {

    void onCategoryDataSuccess(TopBean topBean);
    void onCategoryDataFailure(String msg);
}
