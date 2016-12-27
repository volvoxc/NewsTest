package com.news.gemens.newstest.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.gemens.newstest.R;
import com.news.gemens.newstest.news.view.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsFragment extends Fragment {

    private ViewPager viewPager;
    private String[] tabs;
    private List<Fragment> fragmentList;

    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        initFragments();

        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void init() {
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tablayout);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        tabs = getResources().getStringArray(R.array.tabs);
        for (int i = 0; i < tabs.length; i++) {
            NewsListFragment newsListFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",tabs[i]);
            newsListFragment.setArguments(bundle);
            fragmentList.add(newsListFragment);
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

    }
}
