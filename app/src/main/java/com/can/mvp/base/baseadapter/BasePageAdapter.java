package com.can.mvp.base.baseadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by can on 2017/12/5.
 * base tablayout+viewpager适配器
 */
public class BasePageAdapter extends FragmentPagerAdapter {
    private String[] mStrings ;
    private List<Fragment> mfragmentList;

    public BasePageAdapter(FragmentManager fm, String[] strings,List<Fragment> fragments) {
        super(fm);
        mStrings = strings;
        mfragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }

}
