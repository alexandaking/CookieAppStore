package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.bean.RecommendBean;

/**
 * Created by alexandaking on 2017/11/20.
 */

public interface CategoryFragmentView extends BaseView{


    void onCategoryDataSuccess(CategoryBean categoryBean);
    void onCategoryDataFailure(String msg);
}
