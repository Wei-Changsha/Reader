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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.reader.BookShelfFragment;
import com.example.reader.R;
import com.example.reader.bean.BookDetail;
import com.example.reader.bean.ChapterList;
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
    private List< ChapterList.MixToc.Chapters> chaptersList=new ArrayList<>();

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
                            boolean flagDoubleCick = false;

                            @Override
                            public void onClick(View view) {
                                if (!flagDoubleCick){
                                    //BookShelfFragment fragment= (BookShelfFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_shelf);
                                    BookShelfFragment fragment=new BookShelfFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("book",bookDetail.getAuthor());
                                    fragment.setArguments(bundle);
                                    BookShelfFragment.initBook();
                                    Log.d("BookDetailActivity","mmm"+bookShelfList.isEmpty());
                                    Log.d("BookDetailActivity","mmm="+bookDetail.getTitle());
//
                                    FragmentManager fragmentManager=getSupportFragmentManager();
                                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                                    transaction.attach(fragment);
                                    transaction.commit();
                                    btnAddToShelf.setText("移出书架");
                                    flagDoubleCick = true;

                                }else {
                                    btnAddToShelf.setText("加入书架");
                                    flagDoubleCick = false;

                                }



//                                Intent intent=new Intent(BookDetailActivity.this, BookShelfFragment.class);
//                                //bookShelfList.add(bookDetail);
//                                if (bookShelfList!=null)
//                                    intent.putExtra("book", bookDetail);
//                                Log.d("BookDetailActivity","mmm"+bookShelfList.isEmpty());
////                                tag[0] =1;
//                                btnAddToShelf.setText("移出书架");
//                                startActivity(intent);

                            }
                        });
                        btnStartRead.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //访问章节总列表
                                query(bookDetail.get_id());

                            }
                        });

                    }
                });

            }
        });
    }


    //访问章节总列表
    public void query(String id){
        String url="http://api.zhuishushenqi.com/mix-atoc/"+id+"?view=chapters";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
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
                Utility.handleChapterListResponse(responseText,chaptersList);
                String linkUrl=chaptersList.get(0).getLink();
                //String linkUrl=chapters.getLink();
                String title=chaptersList.get(0).getTitle();
                Intent intent=new Intent(BookDetailActivity.this,ReadBookActivity.class);
                intent.putExtra("chapters",chaptersList.get(0));
                startActivity(intent);

                Log.d("BookDetailActivity","llllink="+linkUrl);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });

            }
        });

    }

}


