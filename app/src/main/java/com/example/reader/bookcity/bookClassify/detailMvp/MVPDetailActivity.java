package com.example.reader.bookcity.bookClassify.detailMvp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reader.R;
import com.example.reader.base.BaseActivity;
import com.example.reader.bean.Book;
import com.example.reader.bean.BookD;
import com.example.reader.bean.BookDetail;
import com.example.reader.bean.ChapterList;
import com.example.reader.bookcity.bookClassify.ReadBookActivity;
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

public class MVPDetailActivity extends BaseActivity<MVPDetailPresenter,IDetailContract.V> implements View.OnClickListener{

    private List<BookDetail> bookShelfList = new ArrayList<>();
    private List<ChapterList.MixToc.Chapters> chaptersList = new ArrayList<>();
    private Book book1=new Book();
    private String bookID;

    private ImageView cover;
    private TextView textTitle;
    private TextView textAuthor;
    private TextView textWordCount;
    private TextView textLastChapter;
    private TextView textChapterCount;
    private TextView textUpdated;
    private TextView textLongIntro;

    private Button btnAddToShelf;
    private Button btnStartRead;

    private boolean flagDoubleCick = false;
    private BookDetail mBookDetail;

    @Override
    public void findView() {
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookUrl");
        getContract().requestBookDetail(bookId);//这里才是真正开始的发送请求

        cover = findViewById(R.id.detail_cover_id);

        textTitle = findViewById(R.id.detail_title);
        textAuthor = findViewById(R.id.detail_author);
        textWordCount = findViewById(R.id.detail_word_count);
        textLastChapter = findViewById(R.id.detail_last_chapter);
        textChapterCount = findViewById(R.id.detail_chapter_count);
        textUpdated = findViewById(R.id.detail_updated);
        textLongIntro = findViewById(R.id.detail_long_info);

        btnAddToShelf = findViewById(R.id.bt_add_to_shelf);
        btnStartRead = findViewById(R.id.bt_start_read);

        btnAddToShelf.setOnClickListener(this);
        btnStartRead.setOnClickListener(this);
    }

    public void initView(BookDetail bookDetail) {
        String imageUrl = "http://statics.zhuishushenqi.com" + bookDetail.getCover();
        Glide.with(this).load(imageUrl).into(cover);

        textTitle.setText(bookDetail.getTitle());
        textAuthor.setText("作者：" + bookDetail.getAuthor());
        textWordCount.setText("字数：" + bookDetail.getWordCount());
        textLastChapter.setText("最近更新" + bookDetail.getLastChapter());
        textChapterCount.setText("共" + bookDetail.getChaptersCount() + "章");
        textUpdated.setText("更新时间" + bookDetail.getUpdated());
        textLongIntro.setText(bookDetail.getLongIntro());
    }

    @Override
    public MVPDetailPresenter getPresenterInstance() {
        return new MVPDetailPresenter();
    }

    @Override
    public IDetailContract.V getContract() {

        return new IDetailContract.V() {
            @Override
            public void requestBookDetail(String bookId) {
                mPresenter.getContract().requestBookDetail(bookId);
            }

            @Override
            public void responseBookDetail(final BookDetail bookDetail) {
                mBookDetail = bookDetail;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView(bookDetail);
                    }
                });

            }
        };
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_book_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_to_shelf:{
                if (!flagDoubleCick) {

                    btnAddToShelf.setText("移出书架");
                    flagDoubleCick = true;
                    book1=new Book(mBookDetail.get_id(),mBookDetail.getTitle(),mBookDetail.getLastChapter(),
                            null,mBookDetail.getCover());
                    book1.save();
                    if (book1.save()) {
                        Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show();
                    }
                    List<Book> books= DataSupport.findAll(Book.class);

                    for (Book bookDetail1:books){
                        //Log.d("BookDetailActivity", "fff1=" + bookDetail1.getTitle());
                    }
                } else {
                    btnAddToShelf.setText("加入书架");
                    flagDoubleCick = false;
                    DataSupport.deleteAll(Book.class,"title=?",mBookDetail.getTitle());
                }
                break;
            }
            case R.id.bt_start_read:{
                bookID = mBookDetail.get_id();
                //访问章节总列表   获取不成功
                query(mBookDetail.get_id());
                Log.d("BookDetailActivity", "fff66=" + mBookDetail.get_id());

                break;
            }
            default:
                break;

        }
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
                        Toast.makeText(MVPDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
                    if (bookDS.get(i).getTag()==id) {
                        Log.d("BookDetailActivity", "lll11=true" +id );
                    }
                }

                String title = chaptersList.get(0).getTitle();
                Intent intent = new Intent(MVPDetailActivity.this, ReadBookActivity.class);
                intent.putExtra("chapterslink", chaptersList.get(0).getLink());
                intent.putExtra("bookid",bookID);
                //intent.putExtra("allChapters", (Serializable) chaptersList);数据太大了 传不了
                startActivity(intent);
                Log.d("BookDetailActivity", "llllink=" + id);
            }
        });

    }
}
