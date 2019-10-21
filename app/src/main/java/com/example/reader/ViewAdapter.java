package com.example.reader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//ViewPager的适配器
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
        BookShelfFragment bookShelfFragment=BookShelfFragment.newInstance("this is bookshelf");
        BookCityFragment bookCityFragment=BookCityFragment.newInstance();
        BookCenterFragment bookCenterFragment=BookCenterFragment.newInstance();
        bookShelfFragmentList.add(bookShelfFragment);
        bookShelfFragmentList.add(bookCityFragment);
        bookShelfFragmentList.add(bookCenterFragment);
    }

//    @Override
//    public View getView(int pos,View convertView,ViewGroup parent){
//
//        return convertView;
//
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        initFragment();
        return bookShelfFragmentList.get(position);
//        switch (position){
//            case 0:{
//                Intent intent=new Intent();
//                bookShelfFragmentList.add(BookShelfFragment.newInstance(tabs[position]));
//                 return BookShelfFragment.newInstance(tabs[position]);//返回书架碎片
//            }
//            case 1:{
//                bookShelfFragmentList.add(BookCityFragment.newInstance());
//                return BookCityFragment.newInstance();
//            }
//            case 3:{
//                bookShelfFragmentList.add(BookCityFragment.newInstance());
//                return BookCityFragment.newInstance();
//            }
//            default:
//
//        }
//        //return BookShelfFragment.newInstance(tabs[position]);
//        //return bookShelfFragmentList.get(position);
//        return null;

    }

    @Override
    public int getCount() {
        return 3;
    }

    //Tab标题为对应页通过getPageTitle()返回的文本
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
