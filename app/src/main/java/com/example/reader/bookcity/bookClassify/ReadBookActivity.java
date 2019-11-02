package com.example.reader.bookcity.bookClassify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.reader.R;
import com.example.reader.bean.BookD;
import com.example.reader.bean.ChapterList;
import com.example.reader.bean.chapterDetailBean;
import com.example.reader.bookcity.readbok.ScanView;
import com.example.reader.bookcity.readbok.ScanViewAdapter;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;
import com.github.barteksc.pdfviewer.PDFView;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReadBookActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private PDFView pdfView;
    private TextView readTitle;
    private TextView readBody;
    private TextView readTitle1;
    private TextView readBody1;

    private ScanView scanview;
    private ScanViewAdapter adapter;
    private  String link;
    private String bookid;
    private static String body;

   // private TxtReaderView txtReaderView;
   static List<String> items = new ArrayList<String>();

    private ChapterList.MixToc.Chapters chapters;
    private List< ChapterList.MixToc.Chapters> chaptersList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        //获取动态权限
        getPermission();

        Intent intent=getIntent();
        link= intent.getStringExtra("chapterslink");
        bookid=intent.getStringExtra("bookid");

        //Log.d("ReadBookActivity","jjjid="+bookid);//5c20de57a36be7610fc08827  5c20de57a36be7610fc08827

        //List<ChapterList.MixToc.Chapters> chaptersList= (List<ChapterList.MixToc.Chapters>) intent.getSerializableExtra("allChapters");

        String address="http://chapter2.zhuishushenqi.com/chapter/"+link;
       //Log.d("ReadBookActivity","yyy00="+link);



//        for (int i = 0; i < 8; i++)
//            items.add("第 " + (i + 1) + " 页");

//        List<BookD> bookDS= DataSupport.findAll(BookD.class);
//        if (bookDS!=null&&bookDS.size()>0){
//            for (int i=0;i<bookDS.size();i++){
//                if (bookDS.get(i).getTag()==bookid){
//                    Log.d("ReadBookActivity","vvvxxx"+bookDS.get(i).getTag());
//                    Log.d("ReadBookActivity","vvvxxx"+bookid);
//
//                }
//                items.add(bookDS.get(i).getTitle());
//                Log.d("ReadBookActivity","vvvboogtag"+bookDS.get(i).getTitle());
//            }
//        }
        body="\t亲爱的小伙伴，非常歉意。该书版权到期，不再提供在线阅读服务。我们会竭尽全力继续与版权方协商，感谢您一直以来的支持。\r\n\r\n" +
                "\t安卓手机，推荐您：\r\n\r\n\t在应用商店搜索并安装“追.书.神.器.免.费.版”，30万本正版小说永久免费阅读！每天看书签到还送钱！" +
                "\r\n\r\n苹果手机，推荐您：\r\n\r\n在苹果应用商店下载安装“饭.团.追.书”，正版小说永久免费阅读！每天看书签到还送钱。";
        queryFromServer(address);
        scanview = (ScanView) findViewById(R.id.scanview);
        adapter = new ScanViewAdapter(ReadBookActivity.this, items,body);
        Log.d("ReadBookActivity","ggg000=boby="+body);
        scanview.setAdapter(adapter);

    }

    public void queryFromServer( String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                Toast.makeText(ReadBookActivity.this,"加载失败", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        Toast.makeText(ReadBookActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String responseText=response.body().string();
                final chapterDetailBean.Chapter chapter=Utility.handleChapterDetailResponse(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<BookD> bookDS= DataSupport.findAll(BookD.class);
                        if (bookDS!=null&&bookDS.size()>0){
                            for (int i=0;i<bookDS.size();i++){
                                if (bookDS.get(i).getTag().equals(bookid)){
                                    Log.d("ReadBookActivity","vvvxxx"+bookDS.get(i).getTag());
                                    items.add(bookDS.get(i).getTitle());
                                }
//                        Log.d("ReadBookActivity","vvvtag="+bookDS.get(i).getTag());//5c255f547eb96d2307f2655a
//                        Log.d("ReadBookActivity","vvvtitle="+bookDS.get(i).getTitle());
                            }
                        }
                    }
                });



            }
        });

    }


    private void getPermission() {

        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(ReadBookActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(ReadBookActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(ReadBookActivity.this,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }

            ActivityCompat.requestPermissions(ReadBookActivity.this,
                    PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }

        while ((ContextCompat.checkSelfPermission(ReadBookActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))!= PackageManager.PERMISSION_GRANTED) {

        }

    }


}
