package com.example.reader.bookcenter.discussion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.reader.R;
import com.example.reader.bean.DisDetail;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DisDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_detail);
        Intent intent=getIntent();
        String address=intent.getStringExtra("disUrl");
        queryFromServer(address);
    }

    private void queryFromServer(String address) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(DisDetailActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText=response.body().string();
                final DisDetail.Post post=Utility.handleDisDetailResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView dd_cover=findViewById(R.id.dd_cover);
                        TextView dd_author=findViewById(R.id.dd_author);
                        TextView dd_time=findViewById(R.id.dd_time);
                        TextView dd_tltle=findViewById(R.id.dd_title);
                        TextView dd_content=findViewById(R.id.dd_content);

                        String imageUrl = "http://statics.zhuishushenqi.com" + post.getAuthor().getActivityAvatar();
                        Glide.with(DisDetailActivity.this).load(imageUrl).into(dd_cover);
                        dd_author.setText(post.getAuthor().getNickname());
                        dd_time.setText("发言时间："+post.getCreated());
                        dd_tltle.setText(post.getTitle());
                        dd_content.setText(post.getContent());
                    }
                });
            }
        });
    }
}
