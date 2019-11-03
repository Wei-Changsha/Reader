package com.example.reader.bookcenter.discussion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.R;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DisListActivity extends AppCompatActivity {

    private List<ByDiscussion.Posts> postsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        //全部、默认排序：
        String  address="http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=";
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
                        Toast.makeText(DisListActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText=response.body().string();
                Utility.handleDisListResponse(responseText,postsList);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        LinearLayoutManager layoutManager=new LinearLayoutManager(DisListActivity.this);
                        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        DisListAdapter adapter=new DisListAdapter(postsList,DisListActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new DisListAdapter.OnItemOnClickListener() {
                            @Override
                            public void onItemOnClick(View view, int pos) {
                                ByDiscussion.Posts posts=postsList.get(pos);
                                String disUrl="http://api.zhuishushenqi.com/post/"+posts.get_id();
                                Intent intent=new Intent(DisListActivity.this, DisDetailActivity.class);
                                intent.putExtra("disUrl",disUrl);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongOnClick(View view, int pos) {

                            }
                        });
                    }
                });
            }
        });
    }
}
