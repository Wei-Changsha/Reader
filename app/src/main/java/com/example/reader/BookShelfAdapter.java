package com.example.reader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.bean.Book;

import java.util.List;


public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.ViewHolder> {

    private ImageView imageView;
    private List<Book> bookShelfList;
    private Activity activity;
    private android.app.Fragment fragment;
    //private FragmentActivity activity;
    //private BookShelfFragment bookShelfFragment;


    public BookShelfAdapter(List<Book> bookShelfList, Activity activity){
        this.bookShelfList=bookShelfList;
        this.activity=activity;

    }

    public BookShelfAdapter(List<Book> bookShelfList, android.app.Fragment fragment){
        this.bookShelfList=bookShelfList;
        this.fragment=fragment;
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

    @NonNull
    @Override
    public BookShelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final BookShelfAdapter.ViewHolder holder, final int position) {
        Book bookDetail=bookShelfList.get(position);
        //Log.d("BookShelfAdapter", "pppp"+String.valueOf(bookShelfList.isEmpty()));
        if (bookDetail!=null){
            String imageUrl="http://statics.zhuishushenqi.com"+bookDetail.getCover();
            //Glide.with(fragment).load(imageUrl).into(imageView);
            holder.textBookTitle.setText(bookDetail.getTitle());
            holder.textBookLastChapter.setText(bookDetail.getAuthor());
        }



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
        return bookShelfList.size();
    }
}
