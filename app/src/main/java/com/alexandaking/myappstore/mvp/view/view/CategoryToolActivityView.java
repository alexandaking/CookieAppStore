package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.CategoryToolBean;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface CategoryToolActivityView extends BaseView {
    void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean);
    void onCategoryToolError(String msg);
}
