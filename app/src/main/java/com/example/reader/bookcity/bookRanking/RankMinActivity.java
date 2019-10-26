package com.example.reader.bookcity.bookRanking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.R;
import com.example.reader.bean.rankBean;
import com.example.reader.bookcity.bookClassify.BookDetailActivity;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RankMinActivity extends AppCompatActivity {
    private List<rankBean.Ranking.RankBooks> rankBooksList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        Intent intent=getIntent();
        String address=intent.getStringExtra("rankAddress");
        //String  address="http://api.zhuishushenqi.com/book/by-categories?gender=female&type=hot&major=古代言情&minor=穿越时空&start=0&limit=20";
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
                        Toast.makeText(RankMinActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText=response.body().string();
                Utility.handleRankMinResponse(responseText,rankBooksList);
                Log.d("ClassifyListActivity","oooQ  "+ String.valueOf(rankBooksList.size()));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final LinearLayoutManager layoutManager=new LinearLayoutManager(RankMinActivity.this);
                        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        RankingMinAdapter adapter=new RankingMinAdapter(rankBooksList,RankMinActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new RankingMinAdapter.OnItemOnClickListener() {
                            @Override
                            public void onItemOnClick(View view, int pos) {
                                recyclerView.setVisibility(View.VISIBLE);
                                View view1=layoutManager.findViewByPosition(pos);
                                LinearLayout layout=(LinearLayout)view1;
                                TextView bookID=layout.findViewById(R.id.book_id);
                                String bookUrl="http://api.zhuishushenqi.com/book/"+bookID.getText().toString();
                                Log.d("RankMinActivity","hhh="+bookUrl);

                                Intent intent=new Intent(RankMinActivity.this, BookDetailActivity.class);
                                intent.putExtra("bookUrl",bookUrl);
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
