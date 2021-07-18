package com.example.reader.Api;

import com.example.reader.bean.BookDetail;
import com.example.reader.bean.rankBean;
import com.example.reader.bookcity.bookRanking.ByRanking;
import com.example.reader.data.ClassifyListBody;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    //book/by-categories?gender={gender}&type={type}&major={major}&minor={minor}&start={start}&limit={limit}
    @GET("/book/by-categories")
    Observable<ClassifyListBody> getClassifyList(
            @Query("gender") String gender,
            @Query("type") String type,
            @Query("major") String major,
            @Query("minor") String minor,
            @Query("start") int start,
            @Query("limit") int limit
    ) ;


}
