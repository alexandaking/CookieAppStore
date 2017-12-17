package com.alexandaking.myappstore.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.RecommendAdapter;
import com.alexandaking.myappstore.adapter.top.RecommendTopWrapper;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.RecommendBean;
import com.alexandaking.myappstore.mvp.presenter.impl.RecommendPresenterImpl;
import com.alexandaking.myappstore.mvp.view.activity.AppDetailActivity;
import com.alexandaking.myappstore.mvp.view.view.RecommendFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/10/9.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendPresenterImpl> implements RecommendFragmentView{

    private static final  String TAG = "RecommendFragment";

    @BindView(R.id.rv_recommend)
    RecyclerView mRv ;

    @BindView(R.id.ptr)
    PullToRefreshView ptr;

    @Inject
    public RecommendPresenterImpl recommendPresenter;

    private RecommendBean recommendBean;

    private List<RecommendBean.RecommendAppBean> appBeanList = new ArrayList<>();

    private RecommendAdapter adapter;

    private RecommendTopWrapper topWrapper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected void load() {

        //网络请求操作
        recommendPresenter.getRecommendData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);

        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendAdapter(getContext(),recommendBean.getRecommendAppBeanList());
        topWrapper = new RecommendTopWrapper(getContext(),adapter);
        topWrapper.addData(recommendBean.getBannerList());
        mRv.setAdapter(topWrapper);

        //禁止下拉加载
        ptr.setPullDownEnable(false);
        ptr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下来刷新
            }

            @Override
            public void onLoadMore() {
                //上拉加载更对
                recommendPresenter.getMoreRecommendData(mActivity);
            }
        });

        adapter.setAppItemClickListener(new RecommendAdapter.AppItemClickListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(mActivity, AppDetailActivity.class);
                intent.putExtra("packageName", packageName);
                mActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onRecommendDataSuccess(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        //appBeanList = recommendBean.getRecommendAppBeanList();
        //Log.i(TAG, recommendBean.getBannerList().size()+"");
        //Log.i(TAG, recommendBean.getRecommendAppBeanList().size()+"");
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onMoreRecommendDataSuccess(RecommendBean recommendBean) {
        appBeanList.addAll(recommendBean.getRecommendAppBeanList());
        //将原有数据清空
        adapter.clearData();
        //添加新数据
        adapter.addDataAll(appBeanList);
        //刷新adapter
        topWrapper.notifyDataSetChanged();

        ptr.onFinishLoading();
    }

    @Override
    public void onRecommendDataFailure(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected RecommendPresenterImpl initInjector() {
        //完成依赖注入
        mFragmentComponent.inject(this);
        //返回Presenter
        return recommendPresenter;
    }
}
