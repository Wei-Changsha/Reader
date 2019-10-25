package com.example.reader.bookcity.bookListAvtivity;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reader.R;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.ClassifyListAdapter;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ByList.Book> bookList;//主题书单列表
    private Activity activity;
    private ImageView bookImg;

    public ListAdapter(List<ByList.Book> bookList,Activity  activity){
        this.bookList=bookList;
        this.activity=activity;
    }

    public interface OnItemOnClickListener{
        void onItemOnClick(View view, int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private ListAdapter.OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(ListAdapter.OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }

    //删除函数
    public void removeItem(int pos){
        bookList.remove(pos);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookTag;
        TextView bookInfo;
        TextView bookPersonNum;
        TextView bookSave;
        public ViewHolder(View view) {
            super(view);
            bookImg=view.findViewById(R.id.book_cover_id);
            bookTitle=view.findViewById(R.id.book_title);
            bookAuthor=view.findViewById(R.id.book_author);
            bookTag=view.findViewById(R.id.book_tag);
            bookInfo=view.findViewById(R.id.book_info);
            bookPersonNum=view.findViewById(R.id.book_preson_num);
            bookSave=view.findViewById(R.id.book_save);

        }
    }

    // //创建viewholder实例，将item的布局加载，并传到构造函数中
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ByList.Book bookbean=bookList.get(position);//得到当前bookbean实例
        //对recyclerview的子项数据进行赋值
        String imageUrl=bookbean.getCover();
        Glide.with(activity).load(imageUrl).into(bookImg);

        holder.bookTitle.setText(bookbean.getTitle());//标题
        holder.bookAuthor.setText(bookbean.getAuthor());//作者
        holder.bookTag.setText("共"+bookbean.getBookCount()+"本书");//  改主题书单的书籍数
        holder.bookInfo.setText(bookbean.getDesc());//描述
        holder.bookPersonNum.setText("共"+bookbean.getCollectorCount()+"人收藏");//收藏人数
        holder.bookSave.setText("");//



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
        return bookList.size();
    }

}
