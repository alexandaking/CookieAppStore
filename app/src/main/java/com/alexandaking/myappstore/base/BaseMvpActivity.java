package com.alexandaking.myappstore.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.alexandaking.myappstore.base.mvpbase.BasePresenter;
import com.alexandaking.myappstore.base.mvpbase.BaseView;
import com.alexandaking.myappstore.di.component.ActivityComponent;
import com.alexandaking.myappstore.di.component.DaggerActivityComponent;
import com.alexandaking.myappstore.di.module.ActivityModule;

/**
 * Created by alexandaking on 2017/10/25.
 *
 * Activity实现MVP的基类
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent mActivityComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector();
        //绑定View
        mPresenter.attachView(this);
        initData();
    }

    public void initActivityComponent(){
        mActivityComponent = DaggerActivityComponent.builder()
                        .activityModule(new ActivityModule(this))
                        .appComponent(((StoreApplication)getApplication())
                        .getApplicationComponent())
                        .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            //解除绑定
            mPresenter.detachView();
        }
    }

    /**
     * 完成注入并返回presenter对象
     * @return
     */
    protected abstract T initInjector();
}
