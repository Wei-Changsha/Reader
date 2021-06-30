package com.example.reader.bookcity.bookRanking;

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

public class RankingActivity extends AppCompatActivity {
    private List<ByRanking.Female> femaleList;
    private List<ByRanking.Male> maleList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        String  address="http://api.zhuishushenqi.com/ranking/gender";
        queryFromServer(address);
    }

    public void queryFromServer( String address){
        //Observable<ByRanking> maleAndFemale = BookApi.getInstance();
        //Subscription s = BookApi.getInstance()

        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(RankingActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText=response.body().string();
                Utility.handleBookRankResponse(responseText,maleList);
                Log.d("RankingActivity","rrr size0=  "+ maleList.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager layoutManager=new LinearLayoutManager(RankingActivity.this);
                        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        RankingAdapter adapter=new RankingAdapter(maleList,RankingActivity.this);
                        Log.d("RankingActivity","rrr size=  "+ maleList.size());
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new RankingAdapter.OnItemOnClickListener() {
                            @Override
                            public void onItemOnClick(View view, int pos) {
                                ByRanking.Male maleRank=maleList.get(pos);
                                String rankUrl="http://api.zhuishushenqi.com/ranking/"+maleRank.get_id();
                                Intent intent=new Intent(RankingActivity.this, RankMinActivity.class);
                                intent.putExtra("rankAddress",rankUrl);
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
