package com.alexandaking.myappstore.mvp.view.fragment;


import android.os.Bundle;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.section.AppRecommendHotSection;
import com.alexandaking.myappstore.adapter.section.AppRecommendPopularSection;
import com.alexandaking.myappstore.adapter.section.AppRecommendTasteSection;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.AppRecommendBean;
import com.alexandaking.myappstore.mvp.presenter.impl.AppRecommendPresenterImpl;
import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.view.AppRecommendFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/12/6.
 */

public class AppRecommendFragment extends BaseMvpFragment<AppRecommendPresenterImpl> implements AppRecommendFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppRecommendPresenterImpl appRecommendPresenter;

    private String packageName ;

    private AppRecommendBean appRecommendBean ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity)getActivity()).getDetailPackageName();
        show();
    }

    @Override
    public void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean) {
        this.appRecommendBean = appRecommendBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected View createSuccessView() {

        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
        ButterKnife.bind(this,view);

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",appRecommendBean.getPopularAppBeanList(),packageName));
        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",appRecommendBean.getTasteAppBeanList(),packageName));
        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",appRecommendBean.getHotAppBeanList(),packageName));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    protected void load() {
        appRecommendPresenter.getAppRecommendData(mActivity,packageName);
    }

    @Override
    protected AppRecommendPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appRecommendPresenter;
    }
}
