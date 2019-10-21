package com.example.reader.bookcity.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.ByCategories;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Utility {
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

            Bookbean mBookbean=new Bookbean(bookbean.getTitle(),
                    bookbean.getAuthor(),bookbean.getMajorCate(),bookbean.getMinorCate(),
                    bookbean.getLatelyFollower(),bookbean.getRetentionRatio());
            //bookbean.save();
            bookbeanList.add(mBookbean);
            Log.d("Utility", "uuu"+String.valueOf(bookbeanList.isEmpty()));
        }


    }
}
