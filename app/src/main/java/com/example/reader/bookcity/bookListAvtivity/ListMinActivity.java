package com.example.reader.bookcity.bookListAvtivity;

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
import com.example.reader.bean.ListBean;
import com.example.reader.bean.rankBean;
import com.example.reader.bookcity.bookClassify.detailMvp.MVPDetailActivity;
import com.example.reader.bookcity.bookRanking.RankingMinAdapter;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListMinActivity extends AppCompatActivity {
    private List<ListBean.BookList.Books.Book> bookMinList=new ArrayList<>();
    private List<rankBean.Ranking.RankBooks> rankBooksList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        Intent intent=getIntent();
        String address=intent.getStringExtra("bookUrl");
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
                        Toast.makeText(ListMinActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText=response.body().string();
                Utility.handleListMinResponse(responseText,bookMinList);
                for (ListBean.BookList.Books.Book book:bookMinList){
                    rankBean.Ranking.RankBooks mBook=new rankBean.Ranking.RankBooks(
                            book.get_id(),book.getTitle(),book.getAuthor(),book.getMajorCate(),
                            book.getCover(),book.getSite(),book.getBanned(),book.getLatelyFollower(),
                            String.valueOf(book.getRetentionRatio()));
                    rankBooksList.add(mBook);
                    Log.d("ListMinActivity","ooozzz  "+ book.getTitle());
                }
                Log.d("ListMinActivity","oooQ  "+ String.valueOf(bookMinList.size()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final LinearLayoutManager layoutManager=new LinearLayoutManager(ListMinActivity.this);
                        final RecyclerView recyclerView=findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        RankingMinAdapter adapter=new RankingMinAdapter(rankBooksList,ListMinActivity.this);
                        recyclerView.setAdapter(adapter);
                        Log.d("RankMinActivity","hhhrr="+rankBooksList.size());
                        adapter.setOnItemClickListener(new RankingMinAdapter.OnItemOnClickListener() {
                            @Override
                            public void onItemOnClick(View view, int pos) {
                                recyclerView.setVisibility(View.VISIBLE);
                                View view1=layoutManager.findViewByPosition(pos);
                                LinearLayout layout=(LinearLayout)view1;
                                TextView bookID=layout.findViewById(R.id.book_id);
                                String bookUrl="http://api.zhuishushenqi.com/book/"+bookID.getText().toString();
                                Log.d("RankMinActivity","rrrrrxjava="+bookUrl);

                               // Intent intent=new Intent(ListMinActivity.this, rxDetailActivity.class);
                                Intent intent=new Intent(ListMinActivity.this, MVPDetailActivity.class);
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
