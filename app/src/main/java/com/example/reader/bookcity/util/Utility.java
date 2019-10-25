package com.example.reader.bookcity.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reader.bean.BookDetail;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.ByCategories;
import com.example.reader.bookcity.bookClassify.ClassifyListActivity;
import com.example.reader.bookcity.bookListAvtivity.ByList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Utility extends AppCompatActivity implements Serializable{
    //解析和处理从服务器返回的分类列表书籍数据
    public static void handleBookDataResponse(String response, List<Bookbean> bookbeanList){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("books");
        //Gson gson=new Gson();
        for (JsonElement book:jsonArray){
            Bookbean bookbean=new Gson().fromJson(book,new TypeToken<Bookbean>()
            {}.getType());
            Log.d("Utility","uuu"+bookbean.getTitle());

            String imageUrl="http://statics.zhuishushenqi.com"+bookbean.getCover();
            Bookbean mBookbean=new Bookbean(bookbean.get_id(),imageUrl,bookbean.getTitle(),
                    bookbean.getAuthor(),bookbean.getMajorCate(),bookbean.getShortIntro(),
                    bookbean.getLatelyFollower(),bookbean.getRetentionRatio());

            bookbeanList.add(mBookbean);
            Log.d("Utility", "uuua"+bookbean.get_id());
        }
    }

    //解析主题书单
    public static void handleBookListResponse(String response, List<ByList.Book> bookbeanList){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("bookLists");
        for (JsonElement book:jsonArray){
            ByList.Book bookbean=new Gson().fromJson(book,new TypeToken<ByList.Book>()
            {}.getType());

            Log.d("Utility","uuu"+bookbean.getTitle());
            String imageUrl="http://statics.zhuishushenqi.com"+bookbean.getCover();
            ByList.Book mBookbean=new ByList.Book(bookbean.get_id(),bookbean.getTitle(),
                    bookbean.getAuthor(),bookbean.getDesc(),bookbean.getGender(),
                    bookbean.getCollectorCount(),imageUrl,bookbean.getBookCount());

            bookbeanList.add(mBookbean);

        }

    }

    public static BookDetail handleBookDetailResponse(String response){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        jsonObject.get("title").getAsString();
        Log.d("Utility","rrr1"+jsonObject.get("title").getAsString());
        //再转JsonArray 加上数据头
        BookDetail bookDetail=new Gson().fromJson(response,BookDetail.class);
        String bookDetailAddress="http://api.zhuishushenqi.com/mix-atoc/"+bookDetail.get_id()+"?view=chapters";
        Log.d("Utility","rrr"+bookDetail.getTitle());

        return bookDetail;

    }
    public static BookDetail handleBookDetailResponse(String response,List<BookDetail> booklist){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        jsonObject.get("title").getAsString();
        Log.d("Utility","rrr1"+jsonObject.get("title").getAsString());
        //再转JsonArray 加上数据头
        BookDetail bookDetail=new Gson().fromJson(response,BookDetail.class);
        String bookDetailAddress="http://api.zhuishushenqi.com/mix-atoc/"+bookDetail.get_id()+"?view=chapters";
        Log.d("Utility","rrr"+bookDetail.getTitle());
        booklist.add(bookDetail);
        return bookDetail;

    }




}
