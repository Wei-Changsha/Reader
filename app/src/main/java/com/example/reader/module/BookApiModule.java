package com.example.reader.module;

import com.example.reader.Api.BookApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author wei
 */

public class BookApiModule {

    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true); // 失败重发
//                .addInterceptor(new HeaderInterceptor())
//                .addInterceptor(logging);
        return builder.build();
    }

    public BookApi provideBookService(OkHttpClient okHttpClient) {
        return BookApi.getInstance(okHttpClient);
    }
}
