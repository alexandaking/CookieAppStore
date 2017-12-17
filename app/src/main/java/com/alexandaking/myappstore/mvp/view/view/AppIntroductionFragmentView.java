package com.alexandaking.myappstore.mvp.view.view;

import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.bean.AppIntroductionBean;

/**
 * Created by alexandaking on 2017/12/9.
 */

public interface AppIntroductionFragmentView extends BaseView{

    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);
    void onAppIntroductionDataError(String msg);
}
