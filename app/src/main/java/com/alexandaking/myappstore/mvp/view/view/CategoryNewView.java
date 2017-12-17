package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.CategoryNewBean;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface CategoryNewView extends BaseView {
    void onCategoryNewDataSuccess(CategoryNewBean categoryNewBean);
    void onCategoryNewDataError(String msg);
}
