package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;

import java.util.List;

/**
 * Created by alexandaking on 2017/12/13.
 */

public interface CategorySubjectView extends BaseView {
    void onCategorySubjectDataSuccess(List<String> list) ;
    void onCategorySubjectDataError(String msg);
}