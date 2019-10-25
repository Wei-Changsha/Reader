package com.example.reader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reader.bean.BookDetail;
import java.util.List;


public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.ViewHolder> {

    private ImageView imageView;
    private List<BookDetail> bookShelfList;
    private Activity activity;


    public BookShelfAdapter(List<BookDetail> bookShelfList,Activity activity){
        this.bookShelfList=bookShelfList;
        this.activity=activity;

    }

    public interface OnItemOnClickListener{
        void onItemOnClick(View view, int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }

    @NonNull
    @Override
    public BookShelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textBookTitle;
        TextView textBookLastChapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textBookTitle=itemView.findViewById(R.id.shelf_book_title);
            textBookLastChapter=itemView.findViewById(R.id.shelf_last_chapter);
            imageView=itemView.findViewById(R.id.shelf_cover);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BookShelfAdapter.ViewHolder holder, int position) {

        BookDetail bookDetail=bookShelfList.get(position);
        String imageUrl="http://statics.zhuishushenqi.com"+bookDetail.getCover();
        Glide.with(activity).load(imageUrl).into(imageView);
        holder.textBookTitle.setText(bookDetail.getTitle());
        holder.textBookLastChapter.setText(bookDetail.getLastChapter());


    }






    @Override
    public int getItemCount() {
        return bookShelfList.size();
    }
}
