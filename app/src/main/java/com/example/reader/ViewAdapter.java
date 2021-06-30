package com.example.reader;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//ViewPager的适配器  主页面
public class ViewAdapter extends FragmentPagerAdapter implements Serializable {

    List<BookShelfFragment> bookShelfFragmentList=new ArrayList<>();
    String[] tabs = {"书架","书城","中心"};
    public ViewAdapter(@NonNull FragmentManager fm){
        super(fm);
    }

    public ViewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    //初始化碎片集合
    private void initFragment(){
        BookShelfFragment bookShelfFragment = BookShelfFragment.newInstance("this is bookshelf");
        BookCityFragment bookCityFragment = BookCityFragment.newInstance();
        BookCenterFragment bookCenterFragment = BookCenterFragment.newInstance();
        bookShelfFragmentList.add(bookShelfFragment);
        bookShelfFragmentList.add(bookCityFragment);
        bookShelfFragmentList.add(bookCenterFragment);
    }
    
    @NonNull
    @Override
    public Fragment getItem(int position) {
        initFragment();
        return bookShelfFragmentList.get(position);
    }

    @Override
    public int getCount() {
        //return 3;
        //return bookShelfFragmentList.size();
        return tabs.length;
    }



    //Tab标题为对应页通过getPageTitle()返回的文本,
    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
