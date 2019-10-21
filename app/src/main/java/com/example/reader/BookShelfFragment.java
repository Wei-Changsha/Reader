package com.example.reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class BookShelfFragment extends Fragment {
    private static final String ARG_SECTION="section";

    public BookShelfFragment(){

    }


    //实例化碎片的构造方法
    public static BookShelfFragment newInstance(String setion){
        BookShelfFragment fragment=new BookShelfFragment();
        Bundle args=new Bundle();
        args.putString(ARG_SECTION,setion);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_book_shelf,container,false);
        TextView textView =view.findViewById(R.id.show);
        textView.setText(getArguments().getString(ARG_SECTION));
        return view;
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }


}
