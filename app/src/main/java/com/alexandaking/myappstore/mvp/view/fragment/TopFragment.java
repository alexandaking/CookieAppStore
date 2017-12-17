package com.alexandaking.myappstore.mvp.view.fragment;

import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.section.TopSection;
import com.alexandaking.myappstore.adapter.top.TopTopWrapper;
import com.alexandaking.myappstore.base.BaseFragment;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.AppBean;
import com.alexandaking.myappstore.bean.TopBean;
import com.alexandaking.myappstore.mvp.presenter.TopFragmentPresenter;
import com.alexandaking.myappstore.mvp.presenter.impl.TopPresenterImpl;
import com.alexandaking.myappstore.mvp.view.view.TopFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.alexandaking.myappstore.widget.ViewUpSearch;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/10/9.
 */

public class TopFragment extends BaseMvpFragment<TopPresenterImpl> implements TopFragmentView{

    private final static String TAG = "TopFragment";

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.search)
    ViewUpSearch search;

    @Inject
    public TopPresenterImpl topPresenter;

    private TopBean topBean;

    private boolean isExpand;

    @Override
    protected void load() {
        //网络请求操作
        topPresenter.getTopData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_top);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        Map<String, List<AppBean>> appBeanMap = topBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();
        for(String key : strings){
            List<AppBean> appBeanList = appBeanMap.get(key);
            adapter.addSection( new TopSection(getContext(), key, appBeanList));
        }
        TopTopWrapper topTopWrapper = new TopTopWrapper(getContext(),adapter);
        topTopWrapper.adddata(topBean.getTopTopBeanList());

        rv.setAdapter(topTopWrapper);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
                //Log.i(TAG,"firstVisibleItemPosition"+firstVisibleItemPosition);
                //悬浮搜索框的收齐和展开都是在第一个条目时才有效
                //dy > 0 上拉 搜索框收起
                if(firstVisibleItemPosition == 0 && dy < 0 && isExpand){
                    //只有之前是打开状态的时候才会收起
                    search.updateShow(!isExpand);
                    isExpand = false;

                }else if (firstVisibleItemPosition == 0 && dy > 0 && !isExpand){
                    search.updateShow(!isExpand);
                    isExpand = true;
                }
            }
        });
        return view;
    }

    @Override
    public void onCategoryDataSuccess(TopBean topBean) {
        this.topBean = topBean;
        Log.i(TAG,""+topBean.getTopTopBeanList().size());
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onCategoryDataFailure(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TopPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return topPresenter;
    }
}
