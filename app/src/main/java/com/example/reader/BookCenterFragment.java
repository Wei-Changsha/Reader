package com.example.reader;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.knowledge.mnlin.linemenuview.LineMenuListener;
import com.knowledge.mnlin.linemenuview.LineMenuView;

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
                switch(menuItem.getItemId()) {
                    case R.id.book_review:{
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
