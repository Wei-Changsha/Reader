package com.example.reader.Api;

import com.example.reader.bean.BookDetail;
import com.example.reader.bean.Bookbean;
import com.example.reader.bean.CONSTANT;
import com.example.reader.bean.rankBean.Ranking;
import com.example.reader.bookcity.bookRanking.ByRanking;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author wei
 * 单例模式Singleton
 */
public class BookApi {
    public static volatile BookApi instance;
    private BookApiService service;

    public BookApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANT.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))//Gson数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjava网络请求适配器
                .client(okHttpClient)
                .build();
        service = retrofit.create(BookApiService.class);
    }

    public static BookApi getInstance(OkHttpClient okHttpClient) {
        if(instance == null) {
            synchronized (BookApi.class) {
                if(instance == null) {
                    instance = new BookApi(okHttpClient);
                }
            }
        }
        return instance;
    }

    public Observable<BookDetail> getBookDetail(String bookId) {
        return service.getBookDetail(bookId);
    }

    public Observable<ByRanking> getByRanking() {
        return service.getMaleAndMale();
    }

    public Observable<List<Ranking.RankBooks>> getRankBooks(String rankingId) {
        return service.getOneRankingList(rankingId);
    }

    public Observable<List<Bookbean>> getClassifyList(String gnder,String type, String major, String minor, int start, int limit ) {
        return service.getClassifyList(gnder, type, major, minor, start, limit);
    }
}
