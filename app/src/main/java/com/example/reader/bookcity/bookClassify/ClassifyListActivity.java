package com.example.reader.bookcity.bookClassify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.reader.R;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ClassifyListActivity extends AppCompatActivity {

    private List<Bookbean> bookbeanList=new ArrayList<>();//recyclerview数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);

        String  address="http://api.zhuishushenqi.com/book/by-categories?gender=male&type=hot&major=玄幻&minor=东方玄幻&start=0&limit=20";
        queryFromServer(address);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout
        Log.d("ClassifyListActivity","oooc  "+ String.valueOf(bookbeanList.size()));
        ClassifyListAdapter adapter=new ClassifyListAdapter(bookbeanList);//将list数据传到适配器中;
        recyclerView.setAdapter(adapter);
        //recyclerView点击的监听器
        adapter.setOnItemClickListener(new ClassifyListAdapter.OnItemOnClickListener() {
            @Override//短暂点击事件
            public void onItemOnClick(View view, int pos) {
                Toast.makeText(ClassifyListActivity.this," ccc  short kik ", Toast.LENGTH_SHORT).show();
            }

            @Override//长按点击事件
            public void onItemLongOnClick(View view, int pos) {

            }
        });
    }

    private void initBook(){
        List<Bookbean> bookbeans= DataSupport.findAll(Bookbean.class);
        // Bookbean mBookbean;
        for (Bookbean bookbean:bookbeans){
            Log.d("ClassifyListActivity","kkk"+bookbean.getTitle());
            Bookbean mBookbean=new Bookbean(bookbean.getTitle(),
                    bookbean.getAuthor(),bookbean.getMajorCate(),bookbean.getMinorCate(),
                    bookbean.getLatelyFollower(),bookbean.getRetentionRatio());
            bookbeanList.add(mBookbean);
        }

    }

    public void queryFromServer(String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(ClassifyListActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText=response.body().string();
                Utility.handleBookDataResponse(responseText,bookbeanList);
                Log.d("ClassifyListActivity","oooQ  "+ String.valueOf(bookbeanList.size()));


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ClassifyListActivity","oooQq  "+ String.valueOf(bookbeanList.size()));
                        Toast.makeText(ClassifyListActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Log.d("ClassifyListActivity","oooA  "+ String.valueOf(bookbeanList.size()));

    }



}
