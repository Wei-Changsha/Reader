package com.example.reader.bookcity.bookRanking;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reader.R;
import com.example.reader.bean.rankBean;

import java.util.List;

public class RankingMinAdapter extends RecyclerView.Adapter<RankingMinAdapter.ViewHolder> {

    private List<rankBean.Ranking.RankBooks> rankBooksList;
    private Activity activity;
    private ImageView bookImg;

    public RankingMinAdapter(List<rankBean.Ranking.RankBooks> rankBooksList,Activity activity){
        this.rankBooksList=rankBooksList;
        this.activity=activity;
    }

    public interface OnItemOnClickListener{
        void onItemOnClick(View view,int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private RankingMinAdapter.OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(RankingMinAdapter.OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookTitle;
        TextView bookAuthor;
        TextView bookTag;
        TextView bookInfo;
        TextView latelyFollower;
        TextView retentionRatio;
        TextView bookID;
        public ViewHolder(@NonNull View view) {
            super(view);
            bookImg=view.findViewById(R.id.book_cover_id);
            bookTitle=view.findViewById(R.id.book_title);
            bookAuthor=view.findViewById(R.id.book_author);
            bookTag=view.findViewById(R.id.book_tag);
            bookInfo=view.findViewById(R.id.book_info);
            latelyFollower=view.findViewById(R.id.book_preson_num);
            retentionRatio=view.findViewById(R.id.book_save);
            bookID=view.findViewById(R.id.book_id);
        }
    }
    @NonNull
    @Override
    public RankingMinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RankingMinAdapter.ViewHolder holder, final int position) {

        rankBean.Ranking.RankBooks book=rankBooksList.get(position);
        String imageUrl="http://statics.zhuishushenqi.com"+book.getCover();
        Glide.with(activity).load(imageUrl).into(bookImg);
        Log.d("RankingMinActivity","oooc"+ String.valueOf(rankBooksList.size()));
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookTag.setText("");
        holder.bookInfo.setText(""+book.getShortIntro());
        holder.latelyFollower.setText(book.getLatelyFollower()+"人追更");
        holder.retentionRatio.setText(book.getRetentionRatio()+"%人保存");//保存比例
        holder.bookID.setText(book.get_id());

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

    @Override
    public int getItemCount() {
        return rankBooksList.size();
    }


}
