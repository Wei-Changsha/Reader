package com.example.reader.bookcity.bookClassify;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.R;
import com.example.reader.bean.Bookbean;

import java.util.List;

public class ClassifyListAdapter extends RecyclerView.Adapter<ClassifyListAdapter.ViewHolder> {

    private List<Bookbean> bookbeanList;

    public ClassifyListAdapter(List<Bookbean> bookbeanList1){
        bookbeanList=bookbeanList1;
    }

    public interface OnItemOnClickListener{
        void onItemOnClick(View view,int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }

    //删除函数
    public void removeItem(int pos){
        bookbeanList.remove(pos);
        notifyItemRemoved(pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView bookImg;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookTag;
        TextView bookInfo;
        TextView bookPersonNum;
        TextView bookSave;
        public ViewHolder(View view) {
            super(view);
            //bookImg=view.findViewById(R.id.book_cover_id);
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
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Bookbean bookbean=bookbeanList.get(position);//得到当前bookbean实例
        //对recyclerview的子项数据进行赋值
        //holder.bookImg.setImageIcon(bookbean.get_id());
        Log.d("ClassifyListActivity","oooc"+ String.valueOf(bookbeanList.size()));
        holder.bookTitle.setText(bookbean.getTitle());
        holder.bookAuthor.setText(bookbean.getAuthor());
        holder.bookTag.setText(bookbean.getMajorCate());
        holder.bookInfo.setText(bookbean.getShortIntro());
        holder.bookPersonNum.setText(bookbean.getLatelyFollower());
        holder.bookSave.setText(bookbean.getRetentionRatio());//保存比例

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
        return bookbeanList.size();
    }



}
