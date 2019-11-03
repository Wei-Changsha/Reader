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
import com.example.reader.R;
import com.example.reader.bean.Book;
import com.example.reader.bean.BookD;
import com.example.reader.bean.BookDetail;
import com.example.reader.bean.ChapterList;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookDetailActivity extends AppCompatActivity {

    private List<BookDetail> bookShelfList = new ArrayList<>();
    private List<ChapterList.MixToc.Chapters> chaptersList = new ArrayList<>();
    private Book book1=new Book();
    private String bookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent = getIntent();
        String bookUrl = intent.getStringExtra("bookUrl");
        Log.d("BookDetailActivity", "fff777=" + bookUrl);
        queryFromServer(bookUrl);

    }

    public static List<BookDetail> initBook(List<BookDetail> bookList) {
        return bookList;
    }

    public void queryFromServer(String bookId) {
        HttpUtil.sendOkHttpRequest(bookId, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(BookDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText = response.body().string();
                final BookDetail bookDetail = Utility.handleBookDetailResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView cover = findViewById(R.id.detail_cover_id);
                        TextView textTitle = findViewById(R.id.detail_title);
                        TextView textAuthor = findViewById(R.id.detail_author);
                        TextView textWordCount = findViewById(R.id.detail_word_count);
                        TextView textLastChapter = findViewById(R.id.detail_last_chapter);
                        TextView textChapterCount = findViewById(R.id.detail_chapter_count);
                        TextView textUpdated = findViewById(R.id.detail_updated);
                        TextView textLongIntro = findViewById(R.id.detail_long_info);

                        String imageUrl = "http://statics.zhuishushenqi.com" + bookDetail.getCover();
                        Glide.with(BookDetailActivity.this).load(imageUrl).into(cover);
                        textTitle.setText(bookDetail.getTitle());
                        textAuthor.setText("作者：" + bookDetail.getAuthor());
                        textWordCount.setText("字数：" + bookDetail.getWordCount());
                        textLastChapter.setText("最近更新" + bookDetail.getLastChapter());
                        textChapterCount.setText("共" + bookDetail.getChaptersCount() + "章");
                        textUpdated.setText("更新时间" + bookDetail.getUpdated());
                        textLongIntro.setText(bookDetail.getLongIntro());

                        final Button btnAddToShelf = findViewById(R.id.bt_add_to_shelf);
                        final Button btnStartRead = findViewById(R.id.bt_start_read);

                        Log.d("BookDetailActivity", "fff666=" + bookDetail.get_id());
                        Log.d("BookDetailActivity", "fff666=" + bookDetail.getAuthor());

                        btnAddToShelf.setOnClickListener(new View.OnClickListener() {
                             boolean flagDoubleCick;

                            @Override
                            public void onClick(View view) {
                                if (!flagDoubleCick) {

                                    Log.d("BookDetailActivity", "mmm" + bookShelfList.isEmpty());
                                    Log.d("BookDetailActivity", "mmm=" + bookDetail.getTitle());
                                    btnAddToShelf.setText("移出书架");
                                    flagDoubleCick = true;
                                    book1=new Book(bookDetail.get_id(),bookDetail.getTitle(),bookDetail.getLastChapter(),
                                            null,bookDetail.getCover());
                                    book1.save();
                                    if (book1.save()) {
                                        Toast.makeText(BookDetailActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BookDetailActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
                                    }

                                    Log.d("BookDetailActivity", "fff2=" + bookDetail.getTitle());
                                    List<Book> books= DataSupport.findAll(Book.class);
                                    Log.d("BookDetailActivity", "fff4=" + books.size());
                                    for (Book bookDetail1:books){
                                        Log.d("BookDetailActivity", "fff1=" + bookDetail1.getTitle());

                                    }
                                    Log.d("BookDetailActivity", "fff3=" + bookDetail.getTitle());
                                    //finish();

                                } else {
                                    btnAddToShelf.setText("加入书架");
                                    flagDoubleCick = false;
                                    DataSupport.deleteAll(Book.class,"title=?",bookDetail.getTitle());
                                }

                            }
                        });
                        btnStartRead.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bookID=bookDetail.get_id();
                                //访问章节总列表
                                query(bookDetail.get_id());
                                Log.d("BookDetailActivity", "fff66=" + bookDetail.get_id());

                            }
                        });

                    }
                });

            }
        });
    }


    //访问章节总列表
    public void query(final String id) {
        String url = "http://api.zhuishushenqi.com/mix-atoc/" + id + "?view=chapters";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(BookDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText = response.body().string();
                Utility.handleChapterListResponse(responseText, chaptersList);
                String linkUrl = chaptersList.get(0).getLink();
                book1.setLink(linkUrl);

                List<BookD> bookDS= DataSupport.findAll(BookD.class);
                for (int i=0;i<bookDS.size()-1;i++){
                    bookDS.get(i).setBody(chaptersList.get(0).getLink());//保存body到bookD
                    if (bookDS.get(i).getTag()==id)
                        Log.d("BookDetailActivity", "lll11=true" +id );
                }

                String title = chaptersList.get(0).getTitle();
                Intent intent = new Intent(BookDetailActivity.this, ReadBookActivity.class);
                intent.putExtra("chapterslink", chaptersList.get(0).getLink());
                intent.putExtra("bookid",bookID);
                //intent.putExtra("allChapters", (Serializable) chaptersList);数据太大了 传不了
                startActivity(intent);
                Log.d("BookDetailActivity", "llllink=" + id);


            }
        });

    }
}


