package com.example.administrator.tempele.stock.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 2014wang on 2016/4/24.
 */
public class StockCatgFragment extends Fragment {
    @Bind(R.id.stockcatg_tab)
    PagerSlidingTabStrip tabLayout;
    @Bind(R.id.stockcatg_viewpager)
    ViewPager viewpager;
    public StockCatgFragment() {
    }

    public static StockCatgFragment newInstance() {
        StockCatgFragment fragment = new StockCatgFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_catg, null);
        ButterKnife.bind(this, view);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        myPagerAdapter.addFragment(new ShengHu_Fragment(),"深沪");
        myPagerAdapter.addFragment(new HongK_Fragment(),"香港");
        myPagerAdapter.addFragment(new Amric_Fragment(),"美国");
        viewpager.setAdapter(myPagerAdapter);
        tabLayout.setShouldExpand(true);
        tabLayout.setTextSize(28);
        tabLayout.setDividerColor(0xffffff);
        tabLayout.setViewPager(viewpager);
        return view;
    }


    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private  final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
