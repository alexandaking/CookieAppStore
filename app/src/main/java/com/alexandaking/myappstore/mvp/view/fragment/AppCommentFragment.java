package com.alexandaking.myappstore.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.section.AppCommentContactsSection;
import com.alexandaking.myappstore.adapter.top.AppCommentTopWrapper;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.AppCommentBean;
import com.alexandaking.myappstore.mvp.presenter.AppCommentFragmentPresenter;
import com.alexandaking.myappstore.mvp.presenter.impl.AppCommentPresenterImpl;
import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.view.AppCommentFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/12/6.
 */

public class AppCommentFragment extends BaseMvpFragment<AppCommentPresenterImpl> implements AppCommentFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppCommentPresenterImpl appCommentPresenter;

    private String packageName;
    private AppCommentBean appCommentBean;
    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity)getActivity()).getDetailPackageName();
        show();
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_comment);
        ButterKnife.bind(this,view);


        for(AppCommentBean.CommentsBean commentsBean : appCommentBean.getComments()){
            //type为1是精彩评论
            if(commentsBean.getCommentType().equals("1")){
                hotList.add(commentsBean);
            }else{
                list.add(commentsBean);
            }
        }

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());

        if(hotList.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"精彩评论",hotList));
        if(list.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"全部评论",list));

        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(),sectionAdapter);
        appCommentTopWrapper.addDataAll(appCommentBean);
        rv.setAdapter(appCommentTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }

    @Override
    protected void load() {
        appCommentPresenter.getAppCommentData(mActivity, packageName);
    }

    @Override
    public void onAppCommentDataSuccess(AppCommentBean appCommentBean) {
        this.appCommentBean = appCommentBean ;
        setState(LoadingPager.LoadResult.success);

    }

    @Override
    public void onAppCommentDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppCommentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appCommentPresenter;
    }
}
