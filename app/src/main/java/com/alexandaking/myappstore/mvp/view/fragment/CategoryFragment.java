package com.alexandaking.myappstore.mvp.view.fragment;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.section.CategoryContactsSection;
import com.alexandaking.myappstore.adapter.section.CategorySection;
import com.alexandaking.myappstore.adapter.top.CategoryTopWrapper;
import com.alexandaking.myappstore.base.BaseMvpFragment;
import com.alexandaking.myappstore.bean.CategoryBean;
import com.alexandaking.myappstore.mvp.presenter.impl.CategoryPresenterImpl;
import com.alexandaking.myappstore.mvp.view.activity.CategoryNecessaryActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategoryNewActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategorySubjectActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategorySubscribeActivity;
import com.alexandaking.myappstore.mvp.view.activity.CategoryToolActivity;
import com.alexandaking.myappstore.mvp.view.activity.HomeActivity;
import com.alexandaking.myappstore.mvp.view.view.CategoryFragmentView;
import com.alexandaking.myappstore.utils.UIUtils;
import com.alexandaking.myappstore.widget.LoadingPager;
import com.alexandaking.myappstore.widget.ViewUpSearch;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandaking on 2017/10/9.
 */

public class CategoryFragment extends BaseMvpFragment<CategoryPresenterImpl> implements CategoryFragmentView{

    private final static String TAG = "CategoryFragment";

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.search)
    ViewUpSearch search;

    @Inject
    public CategoryPresenterImpl categoryPresenter;

    private CategoryBean categoryBean;

    private boolean isExpand;

    @Override
    protected void load() {
        //网络请求操作
        categoryPresenter.getCategoryData(mActivity);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_category);
        ButterKnife.bind(this, view);

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        CategoryContactsSection categoryContactsSection = new CategoryContactsSection(getContext(),categoryBean.getTitle(),categoryBean.getCategoryDataBeanList());
        adapter.addSection(categoryContactsSection);

        CategoryTopWrapper topWrapper = new CategoryTopWrapper(getContext(), adapter);
        topWrapper.adddata(categoryBean.getCategoryTopBeanList());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(topWrapper);
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

        categoryContactsSection.setOnItemClickListener(new CategoryContactsSection.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),CategoryToolActivity.class) ;
                intent.putExtra("name",categoryBean.getCategoryDataBeanList().get(position).getName());
                ((HomeActivity)getActivity()).startActivity(intent);

            }
        });

        topWrapper.setOnItemClickListener(new CategoryTopWrapper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0){
                    mActivity.startActivity(new Intent(mActivity, CategorySubscribeActivity.class));
                }else if(position == 1){
                    mActivity.startActivity(new Intent(mActivity,CategoryNecessaryActivity.class));
                }else if(position == 2){
                    mActivity.startActivity(new Intent(mActivity,CategoryNewActivity.class));
                }else {
                    mActivity.startActivity(new Intent(mActivity,CategorySubjectActivity.class));
                }
            }
        });

        return view;
    }

    @Override
    public void onCategoryDataSuccess(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onCategoryDataFailure(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected CategoryPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return categoryPresenter;
    }
}
