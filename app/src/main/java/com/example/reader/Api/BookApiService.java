package com.example.reader.Api;

import com.example.reader.bean.BookDetail;
import com.example.reader.bean.Bookbean;
import com.example.reader.bean.rankBean;
import com.example.reader.bookcity.bookRanking.ByRanking;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author wei
 */
public interface BookApiService {
    /**
     * @return ByRanking
     */
    @GET("/ranking/gender")
    Observable<ByRanking> getMaleAndMale();

    @GET("/ranking/{rankingId}")
    Observable<List<rankBean.Ranking.RankBooks>> getOneRankingList(@Path("rankingId") String rankingId);

    @GET("/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);

    @GET("/book/by-categories?gender={male}&type={hot}&major={玄幻}&minor={东方玄幻}&start={0}&limit={20}")
    Observable<List<Bookbean>> getClassifyList(
            @Path("gender") String gender,
            @Path("type") String type,
            @Path("major") String major,
            @Path("minor") String minor,
            @Path("start") int start,
            @Path("limit") int limit) ;


}
