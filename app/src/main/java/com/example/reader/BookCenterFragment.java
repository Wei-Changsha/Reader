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

import com.example.reader.bookcenter.LoginActivity;
import com.example.reader.bookcenter.discussion.DisListActivity;
import com.google.android.material.navigation.NavigationView;

public class BookCenterFragment extends BookShelfFragment {

    public static BookCenterFragment newInstance(){
        BookCenterFragment fragment=new BookCenterFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_book_center,container,false);
        NavigationView navigationView=view.findViewById(R.id.nav_view_center);
        navigationView.setCheckedItem(R.id.book_review);//设置默认选中项

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent=null;
                switch(menuItem.getItemId()) {

                    case R.id.book_review:{
                        intent=new Intent(getActivity(), DisListActivity.class);
                        getActivity().startActivity(intent);
                        Log.d("BookCenterFragment","bbb书评");
                        return true;
                    }
                    case R.id.book_discussion:{
                        Log.d("BookCenterFragment","bbb讨论");
                        return true;
                    }
                    case R.id.book_shortage:{
                        Log.d("BookCenterFragment","bbb书荒");
                        return true;
                    }
                    case R.id.book_login:{
                        intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
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
        inflater.inflate(R.menu.bookcentermenu, menu);

    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

}
