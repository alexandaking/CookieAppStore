package com.alexandaking.myappstore.mvp.presenter;

import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.mvp.view.view.AppIntroductionFragmentView;

/**
 * Created by alexandaking on 2017/12/9.
 */

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView> {
    void getAppIntroductionData(BaseActivity activity, String packageName);
}
