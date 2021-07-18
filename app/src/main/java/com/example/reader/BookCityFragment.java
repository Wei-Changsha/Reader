package com.example.reader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.reader.bookcity.bookClassify.ClassifyActivity;
import com.example.reader.bookcity.bookListAvtivity.ListActivity;
import com.example.reader.bookcity.bookRanking.RankingActivity;
import com.google.android.material.navigation.NavigationView;

import org.litepal.LitePal;


public class BookCityFragment extends BookShelfFragment {
    public static BookCityFragment newInstance(){
        BookCityFragment fragment = new BookCityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_book_city,container,false);
        NavigationView navigationView=view.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.classify);//设置默认选中项

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent=null;
                switch(menuItem.getItemId()) {
                    case R.id.classify:{
                        intent=new Intent(getActivity(), ClassifyActivity.class);
                        getActivity().startActivity(intent);
                        Log.d("BookCityFragment","bbb分类");
                        return true;
                    }
                    case R.id.book_list:{
                        intent=new Intent(getActivity(), ListActivity.class);
                        getActivity().startActivity(intent);
                        Log.d("BookCityFragment","bbb书单");
                        return true;
                    }
                    case R.id.rank_list:{
                        intent=new Intent(getActivity(), RankingActivity.class);
                        getActivity().startActivity(intent);
                        Log.d("BookCityFragment","bbb排行");
                        return true;
                    }
                    case R.id.create_data:{
                        LitePal.getDatabase();
                        Log.d("BookCityFragment","bbb create data");
                        return true;
                    }
                    default:
                }
                return true;
            }
        });
        setHasOptionsMenu(true);//menu

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.bookcitymenu,menu);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }



}
