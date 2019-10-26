package com.example.reader.bookcity.bookClassify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.reader.BookShelfFragment;
import com.example.reader.R;
import com.example.reader.bean.BookDetail;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookDetailActivity extends AppCompatActivity {


    private List<BookDetail> bookShelfList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent=getIntent();
        String bookUrl=intent.getStringExtra("bookUrl");
        queryFromServer(bookUrl);

    }

    public static List<BookDetail>  initBook(List<BookDetail> bookList){
        return bookList;
    }

    public void queryFromServer(String bookId){
        HttpUtil.sendOkHttpRequest(bookId, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(BookDetailActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText=response.body().string();
                final BookDetail bookDetail=Utility.handleBookDetailResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView cover=findViewById(R.id.detail_cover_id);
                        TextView textTitle=findViewById(R.id.detail_title);
                        TextView textAuthor=findViewById(R.id.detail_author);
                        TextView textWordCount=findViewById(R.id.detail_word_count);
                        TextView textLastChapter=findViewById(R.id.detail_last_chapter);
                        TextView textChapterCount=findViewById(R.id.detail_chapter_count);
                        TextView textUpdated=findViewById(R.id.detail_updated);
                        TextView textLongIntro=findViewById(R.id.detail_long_info);

                        String imageUrl="http://statics.zhuishushenqi.com"+bookDetail.getCover();
                        Glide.with(BookDetailActivity.this).load(imageUrl).into(cover);
                        textTitle.setText(bookDetail.getTitle());
                        textAuthor.setText("作者："+bookDetail.getAuthor());
                        textWordCount.setText("字数："+bookDetail.getWordCount());
                        textLastChapter.setText("最近更新"+bookDetail.getLastChapter());
                        textChapterCount.setText("共"+bookDetail.getChaptersCount()+"章");
                        textUpdated.setText("更新时间"+bookDetail.getUpdated());
                        textLongIntro.setText(bookDetail.getLongIntro());

//                        final int[] tag = {0};//1---在书架   0----不在书架
                        final Button btnAddToShelf=findViewById(R.id.bt_add_to_shelf);
                        final Button btnStartRead=findViewById(R.id.bt_start_read);

                        btnAddToShelf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(BookDetailActivity.this, BookShelfFragment.class);
                                //bookShelfList.add(bookDetail);
                                if (bookShelfList!=null)
                                    intent.putExtra("book", bookDetail);
                                Log.d("BookDetailActivity","mmm"+bookShelfList.isEmpty());
//                                tag[0] =1;
                                btnAddToShelf.setText("移出书架");
                                startActivity(intent);

                            }
                        });
                        btnStartRead.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                    }
                });

            }
        });
    }
//    @Override
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.bt_add_to_shelf:{//加入书架和移除书架
//
//                break;
//            }
//            case R.id.bt_start_read:{//开始阅读
//
//                break;
//            }
//
//        }
//
//}

}


