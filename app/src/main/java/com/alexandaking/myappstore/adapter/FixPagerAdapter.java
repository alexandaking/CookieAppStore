package com.alexandaking.myappstore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alexandaking on 2017/10/9.
 */

public class FixPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    private List<Fragment> fragments = null;

    public void setFragments(List<Fragment> fragments){
        this.fragments = fragments;
    }

    public void setTitles(String[] titles){
        this.titles = titles;
    }

    public FixPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 实例化Item
     * instantiateItem 方法中 加载View 并把View add到 container(即 ViewPager)中
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try{
            fragment = (Fragment) super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
