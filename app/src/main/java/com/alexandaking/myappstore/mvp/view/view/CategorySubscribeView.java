package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.CategorySubscribeBean;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface CategorySubscribeView extends BaseView {
    void onCategorySubscribeDataSuccess(CategorySubscribeBean categorySubscribeBean) ;
    void onCategorySubscribeDataError(String msg);
}
