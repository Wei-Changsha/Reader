package com.example.reader.bookcity.bookListAvtivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ListActivity extends AppCompatActivity {
    private List<ByList.Book> bookbeanList=new ArrayList<>();//recyclerview数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        String  address="http://api.zhuishushenqi.com/book-list?duration=all&sort=collectorCount&start=0&limit=20&tag=古代&gender=male";
        queryFromServer(address);
    }

    public void queryFromServer(String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(ListActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText=response.body().string();
                Utility.handleBookListResponse(responseText,bookbeanList);
                Log.d("ClassifyListActivity","oooQ  "+ String.valueOf(bookbeanList.size()));

                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager layoutManager=new LinearLayoutManager(ListActivity.this);
                        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        Log.d("ClassifyListActivity","oooc  "+ bookbeanList.size());
                        ListAdapter adapter=new ListAdapter(bookbeanList,ListActivity.this);//将list数据传到适配器中;

                        recyclerView.setAdapter(adapter);
                        //recyclerView点击的监听器
                        adapter.setOnItemClickListener(new ListAdapter.OnItemOnClickListener() {
                            @Override//短暂点击事件
                            public void onItemOnClick(View view, final int pos) {
                                recyclerView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ByList.Book bookbean=bookbeanList.get(pos);
                                        String bookUrl="http://api.zhuishushenqi.com/book-list/"+bookbean.get_id();
                                        //跳转到书籍详情页
                                        Intent intent=new Intent(ListActivity.this, ListMinActivity.class);
                                        Log.d("ClassifyListActivity","ggg  "+ bookUrl);
                                        intent.putExtra("bookUrl",bookUrl);
                                        startActivity(intent);
                                        Toast.makeText(ListActivity.this,"跳转到书籍详情页 ", Toast.LENGTH_SHORT).show();
                                    }
                                }, 100);
//
                            }

                            @Override//长按点击事件
                            public void onItemLongOnClick(View view, int pos) {

                            }
                        });
                        Log.d("ClassifyListActivity","oooQq  "+ String.valueOf(bookbeanList.size()));
                        Toast.makeText(ListActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


    }
}
