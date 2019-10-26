package com.example.reader.bookcity.bookRanking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reader.R;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<ByRanking.Male> maleList;//主题书单列表

    private Activity activity;
    private ImageView bookImg;



    public interface OnItemOnClickListener{
        void onItemOnClick(View view, int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private RankingAdapter.OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(RankingAdapter.OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }

    @Override
    public int getItemCount() {
        return maleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textRankName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg=itemView.findViewById(R.id.rank_img);
            textRankName=itemView.findViewById(R.id.rank_title);
        }
    }

    public RankingAdapter(List<ByRanking.Male> maleList,Activity activity){
        this.maleList=maleList;
        this.activity=activity;
    }



    @NonNull
    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_list_item,parent,false);
        RankingAdapter.ViewHolder holder=new RankingAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ByRanking.Male male=maleList.get(position);
        String imageUrl="http://statics.zhuishushenqi.com"+male.getCover();
        Glide.with(activity).load(imageUrl).into(bookImg);
        holder.textRankName.setText(male.getTitle());


        if(mOnItemOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemOnClickListener.onItemOnClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemOnClickListener.onItemLongOnClick(holder.itemView,position);
                    return false;
                }
            });
        }
    }


}
