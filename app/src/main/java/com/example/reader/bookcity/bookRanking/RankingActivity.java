package com.example.reader.bookcity.bookRanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.reader.R;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        Intent intent=getIntent();
        //String address=intent.getStringExtra("address");
        String  address="http://api.zhuishushenqi.com/book-list?duration=all&sort=collectorCount&start=0&limit=20&tag=古代&gender=male";
        queryFromServer(address);
    }

    public void queryFromServer(String address){

    }
}
