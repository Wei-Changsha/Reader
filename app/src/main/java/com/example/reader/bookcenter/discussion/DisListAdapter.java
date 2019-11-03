package com.example.reader.bookcenter.discussion;

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

public class DisListAdapter extends RecyclerView.Adapter<DisListAdapter.ViewHolder> {

    private List<ByDiscussion.Posts> postsList;
    private Activity activity;
    private ImageView bookImg;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nickname;
        TextView comment;
        TextView commentCount;
        TextView likeCount;
        TextView dis_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg=itemView.findViewById(R.id.dis_cover_id);
            nickname=itemView.findViewById(R.id.dis_nickname);
            comment=itemView.findViewById(R.id.dis_comment);
            commentCount=itemView.findViewById(R.id.dis_commentCount);
            likeCount=itemView.findViewById(R.id.dis_likeCount);
            dis_id=itemView.findViewById(R.id.dis_id);
        }
    }

    public DisListAdapter(List<ByDiscussion.Posts> postsList, Activity activity) {
        this.postsList = postsList;
        this.activity = activity;
    }

    public interface OnItemOnClickListener{
        void onItemOnClick(View view, int pos);
        void onItemLongOnClick(View view ,int pos);
    }

    //设置监听的方法和声明了一个这个接口的内部变量    供外部来设置监听
    private DisListAdapter.OnItemOnClickListener mOnItemOnClickListener;
    public void setOnItemClickListener(DisListAdapter.OnItemOnClickListener listener){
        this.mOnItemOnClickListener = listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dis_list_item,parent,false);
        DisListAdapter.ViewHolder holder=new DisListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        ByDiscussion.Posts posts=postsList.get(position);
        String imageUrl="http://statics.zhuishushenqi.com"+posts.getAuthor().getActivityAvatar();
        Glide.with(activity).load(imageUrl).into(bookImg);
        holder.dis_id.setText(posts.getAuthor().get_id());
        holder.nickname.setText(posts.getAuthor().getNickname()+"Lv."+posts.getAuthor().getLv());
        holder.comment.setText(posts.getTitle());
        holder.commentCount.setText("□"+posts.getCommentCount());
        holder.likeCount.setText("❤"+posts.getLikeCount());

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
        return postsList.size();
    }
}
