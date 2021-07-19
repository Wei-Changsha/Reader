package com.example.reader.bookcity.bookClassify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.R;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.detailRxjava.rxDetailActivity;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClassifyListActivity extends AppCompatActivity {

    private List<Bookbean> bookbeanList = new ArrayList<>();
    //recyclerview数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        //String  address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=古代言情&minor=穿越时空&start=0&limit=20";
        queryFromServer(address);

    }


    public void queryFromServer(String address) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(ClassifyListActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText = response.body().string();
                Utility.handleBookDataResponse(responseText, bookbeanList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final LinearLayoutManager layoutManager = new LinearLayoutManager(ClassifyListActivity.this);
                        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout


                        final ClassifyListAdapter adapter = new ClassifyListAdapter(bookbeanList, ClassifyListActivity.this);//将list数据传到适配器中;

                        recyclerView.setAdapter(adapter);
                        //recyclerView点击的监听器
                        adapter.setOnItemClickListener(new ClassifyListAdapter.OnItemOnClickListener() {
                            @Override//短暂点击事件
                            public void onItemOnClick(View view, final int pos) {

                                recyclerView.setVisibility(View.VISIBLE);
                                //View view1 = layoutManager.findViewByPosition(pos); view1 == view true
                                LinearLayout layout = (LinearLayout) view;
                                TextView bookID = layout.findViewById(R.id.book_id);

                                //adapter.getViewHolderMap().get(pos);

                                String bookUrl = bookID.getText().toString();
                                //跳转到书籍详情页

                                Intent intent = new Intent(ClassifyListActivity.this, rxDetailActivity.class);
                                intent.putExtra("bookUrl", bookUrl);
                                startActivity(intent);

                            }

                            @Override//长按点击事件
                            public void onItemLongOnClick(View view, int pos) {

                            }
                        });
                    }
                });
            }


        });


    }


}
