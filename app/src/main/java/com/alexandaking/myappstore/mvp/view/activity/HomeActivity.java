package com.alexandaking.myappstore.mvp.view.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.alexandaking.myappstore.R;
import com.alexandaking.myappstore.adapter.FixPagerAdapter;
import com.alexandaking.myappstore.base.BaseActivity;
import com.alexandaking.myappstore.base.BaseFragment;
import com.alexandaking.myappstore.factory.FragmentFactory;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;
    @BindView(R.id.status_bar)
    LinearLayout statusBar ;

    private FixPagerAdapter fixPagerAdapter;
    private String[] titles = {"推荐","分类","排行","管理","我的"};
    private List<Fragment> fragments;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = statusBar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) statusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    statusBar.setLayoutParams(params);
                }
            });
        }
        initViewPagerFragment();
    }

    private void initViewPagerFragment(){
        fixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager());

        fragments = new ArrayList<>();
        for (int i = 0; i<titles.length; i++){
            fragments.add(FragmentFactory.createFragment(i));
        }
        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);

        main_viewpager.setAdapter(fixPagerAdapter);
        //将ViewPager与TabLayout绑定
        tab_layout.setupWithViewPager(main_viewpager);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);

        main_viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();
            }
        });

    }



}