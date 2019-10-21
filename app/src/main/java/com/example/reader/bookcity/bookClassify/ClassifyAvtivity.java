package com.example.reader.bookcity.bookClassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.reader.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassifyAvtivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_avtivity);


        TextView textGuyan=findViewById(R.id.nv_gu_yan);
        textGuyan.setOnClickListener(this);
        TextView textXianyan=findViewById(R.id.nv_xian_yan);
        textXianyan.setOnClickListener(this);
        TextView textXiaoyuan=findViewById(R.id.nv_xiao_yuan);
        textXiaoyuan.setOnClickListener(this);

    }


    @Override
    public void onClick(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.nv_gu_yan:{
                //sendRequestWithOkHttp();
                Log.d("ClassifyActivity","aaa nvguyuan0");
                //跳转到分类列表
                intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
                startActivity(intent);
                Log.d("ClassifyActivity","aaa nvguyuan1");
                break;
            }
            case R.id.nv_xian_yan:{
                Log.d("ClassifyActivity","aaa nvxianyan");
                break;
            }
            case R.id.nv_xiao_yuan:{
                Log.d("ClassifyActivity","aaa nvxiaoyuan");
                break;
            }
        }

    }

//    private void sendRequestWithOkHttp(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.d("ClassifyActivity","ddd Data");
//                    OkHttpClient client=new OkHttpClient();
//                    Request request=new Request.Builder()
//                            .url("http://api.zhuishushenqi.com/book/by-categories")
//                            .build();
//                    Response response=client.newCall(request).execute();
//                    String responseData=response.body().string();
//                    Intent intent=new Intent(ClassifyAvtivity.this,ClassifyListActivity.class);
//                    intent.putExtra("responseData",responseData);
//                    //parseJSONWithGSON(responseData);
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }





}
