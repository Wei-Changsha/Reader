package com.example.reader.bookcity.bookRanking;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.bookcity.bookListAvtivity.ByList;
import com.example.reader.bookcity.bookListAvtivity.ListAdapter;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ByRanking.Male> maleList;//主题书单列表

    private Activity activity;
    private ImageView bookImg;



    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
