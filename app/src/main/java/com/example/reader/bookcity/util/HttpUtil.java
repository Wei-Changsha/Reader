package com.example.reader.bookcity.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                Request request=new Request.Builder().url(address).build();
//                client.newCall(request).enqueue(callback);
//            }
//        }).start();
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
