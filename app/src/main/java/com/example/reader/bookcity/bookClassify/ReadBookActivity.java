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
import com.example.reader.bean.ChapterList;
import com.example.reader.bean.chapterDetailBean;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;
import com.github.barteksc.pdfviewer.PDFView;

import org.jetbrains.annotations.NotNull;

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
    private int p;

   // private TxtReaderView txtReaderView;

    private ChapterList.MixToc.Chapters chapters;
    private List< ChapterList.MixToc.Chapters> chaptersList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        //获取动态权限
        getPermission();

        Intent intent=getIntent();
        chapters= (ChapterList.MixToc.Chapters) intent.getSerializableExtra("chapters");

        Log.d("ReadBookActivity","yyy="+chapters.getTitle());

        String address="http://chapter2.zhuishushenqi.com/chapter/"+chapters.getLink();

        queryFromServer(address);


//        readTitle=findViewById(R.id.read_title);//
//        readBody=findViewById(R.id.read_body);//
//        readTitle1=findViewById(R.id.read_title1);
//        readBody1=findViewById(R.id.read_body1);


    }

    public void queryFromServer( String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
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
                        TextView title=findViewById(R.id.read_title);
                        TextView body=findViewById(R.id.read_body);
                        title.setText(chapters.getTitle());
                        body.setText(chapter.getBody());
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
