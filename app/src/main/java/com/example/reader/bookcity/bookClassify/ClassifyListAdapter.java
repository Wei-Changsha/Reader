package com.example.reader.bookcity.bookClassify;

import android.annotation.SuppressLint;
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
import com.example.reader.bean.Bookbean;
import com.example.reader.bean.CONSTANT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassifyListAdapter extends RecyclerView.Adapter<ClassifyListAdapter.ViewHolder> {

    private List<Bookbean> bookbeanList;
    private Activity activity;
    private ImageView bookImg;
    private Map viewHolderMap = new HashMap<>();


    public Map getViewHolderMap() {
        return viewHolderMap;
    }
    /**
     * 监听事件的接口，包括点击事件和长按事件
     */
    public interface OnItemOnClickListener {
        /**
         * 短点击事件的监听
         * @param view RecyclerView所对应的子项ItemView
         * @param pos  ItemView的位置（可以通过pos获取list的具体子项）
         */
        void onItemOnClick(View view, int pos);

        /**
         * 长按点击事件的监听
         * @param view
         * @param pos
         */
        void onItemLongOnClick(View view, int pos);
    }

    /**
     * 设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
     */
    private OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(OnItemOnClickListener listener) {
        this.mOnItemOnClickListener = listener;

    }

    public ClassifyListAdapter(List<Bookbean> bookbeanList1, Activity activity) {
        this.bookbeanList = bookbeanList1;
        this.activity = activity;
    }


//    //删除函数
//    public void removeItem(int pos){
//        bookbeanList.remove(pos);
//        notifyItemRemoved(pos);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookTag;
        TextView bookInfo;
        TextView bookPersonNum;
        TextView bookSave;
        TextView bookID;

        public ViewHolder(View view) {
            super(view);
            bookImg = view.findViewById(R.id.book_cover_id);
            bookTitle = view.findViewById(R.id.book_title);
            bookAuthor = view.findViewById(R.id.book_author);
            bookTag = view.findViewById(R.id.book_tag);
            bookInfo = view.findViewById(R.id.book_info);
            bookPersonNum = view.findViewById(R.id.book_preson_num);
            bookSave = view.findViewById(R.id.book_save);
            bookID = view.findViewById(R.id.book_id);

        }
    }

    /**
     * 创建viewholder实例，将item的布局加载，并传到构造函数中
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        viewHolderMap.put(position, holder);
        //得到当前bookbean实例
        Bookbean bookbean = bookbeanList.get(position);

        //书籍图片URL
        String imageUrl = CONSTANT.BASE_PIC_URL +  bookbean.getCover();
        //对recyclerview的子项数据进行赋值
        Glide.with(activity).load(imageUrl).into(bookImg);

        holder.bookTitle.setText(bookbean.getTitle());
        holder.bookAuthor.setText(bookbean.getAuthor());

        holder.bookTag.setText(bookbean.getMajorCate());
        holder.bookInfo.setText("简介：" + bookbean.getShortIntro());
        holder.bookPersonNum.setText(bookbean.getLatelyFollower() + "人追更");
        holder.bookSave.setText(bookbean.getRetentionRatio() + "%人保存");
        holder.bookID.setText(bookbean.get_id());

        if (mOnItemOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemOnClickListener.onItemOnClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemOnClickListener.onItemLongOnClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookbeanList.size();
    }

//    public Bitmap getURLimage(String url) {
//
//        Bitmap bmp = null;
//        try {
//            URL myurl = new URL(url);
//            // 获得连接
//            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
//            conn.setConnectTimeout(6000);//设置超时
//            conn.setDoInput(true);
//            conn.setUseCaches(false);//不缓存
//            conn.connect();
//            InputStream is = conn.getInputStream();//获得图片的数据流
//            bmp = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bmp;
//    }

}
