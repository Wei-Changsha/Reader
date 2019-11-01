package com.example.reader.bookcity.util;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reader.bean.Book;
import com.example.reader.bean.BookDetail;
import com.example.reader.bean.Bookbean;
import com.example.reader.bean.ChapterList;
import com.example.reader.bean.ListBean;
import com.example.reader.bean.chapterDetailBean;
import com.example.reader.bean.rankBean;
import com.example.reader.bookcity.bookListAvtivity.ByList;
import com.example.reader.bookcity.bookRanking.ByRanking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;

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

    //处理获取排行总榜
    public static void handleBookRankResponse(String response, List<ByRanking.Male> maleList){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArrayMale = jsonObject.getAsJsonArray("male");
        JsonArray jsonArrayFemale = jsonObject.getAsJsonArray("female");
        for (JsonElement female:jsonArrayFemale){
            ByRanking.Female femaleRank=new Gson().fromJson(female,new TypeToken<ByRanking.Female>()
            {}.getType());
            Log.d("Utility","rrr nv"+femaleRank.getTitle());
            ByRanking.Male maleRank1=new ByRanking.Male(femaleRank.get_id(),femaleRank.getCover(),
                    femaleRank.getTitle(),femaleRank.isCollapse(),femaleRank.getMonthRank(),
                    femaleRank.getTotalRank(),femaleRank.getShortTitle());
            maleList.add(maleRank1);
        }

        for (JsonElement male:jsonArrayMale){
            ByRanking.Male maleRank=new Gson().fromJson(male,new TypeToken<ByRanking.Male>()
            {}.getType());
            Log.d("Utility","rrr nan"+maleRank.getTitle()+maleRank.get_id());
            maleList.add(maleRank);
        }
    }

    //解析更小一级的排行榜
    public static void handleRankMinResponse(String response, List<rankBean.Ranking.RankBooks> rankBooksList){

        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject jsonObject1=jsonObject.getAsJsonObject("ranking");
        JsonArray jsonArray=jsonObject1.getAsJsonArray("books");
        for (JsonElement book:jsonArray){
            rankBean.Ranking.RankBooks rankBooks=new Gson().fromJson(book,new TypeToken<rankBean.Ranking.RankBooks>()
            {}.getType());
            rankBooksList.add(rankBooks);
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

    //处理章节列表数据
    public static void handleChapterListResponse(String response,
                    List< ChapterList.MixToc.Chapters> chaptersList ){
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject jsonObject1=jsonObject.get("mixToc").getAsJsonObject();
        String _id=jsonObject1.get("_id").getAsString();
        JsonArray chapters=jsonObject1.get("chapters").getAsJsonArray();
        //List< ChapterList.MixToc.Chapters> chaptersList=new ArrayList<>();
        for (JsonElement cha:chapters){
            ChapterList.MixToc.Chapters chapters1=new Gson().fromJson(cha,new TypeToken< ChapterList.MixToc.Chapters>()
            {}.getType());
            Log.d("Utility","uuu="+chapters1.getLink());
            chaptersList.add(chapters1);

        }
        Log.d("Utility","uuusize="+chaptersList.size());
        //return chaptersList;
    }

    public static void handleListMinResponse(String response, List<ListBean.BookList.Books.Book> bookList){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject jsonObject1=jsonObject.get("bookList").getAsJsonObject();
        JsonObject jsonObject2=jsonObject1.get("author").getAsJsonObject();
        String  nickname=jsonObject2.get("nickname").getAsString();
        Log.d("Utility","kkk000="+ nickname);

        JsonArray books=jsonObject1.get("books").getAsJsonArray();
        Log.d("Utility","kkktttitle="+books.size());


        for (JsonElement book:books){
            ListBean.BookList.Books theBooks=new Gson().fromJson(book,new TypeToken<ListBean.BookList.Books>()
            {}.getType());
            ListBean.BookList.Books.Book theBook=theBooks.getBook();
            String comment=theBooks.getComment();
            Log.d("Utility","kkkt=comment"+comment);
            Log.d("Utility","kkkt=title "+theBook.getTitle());
            Log.d("Utility","kkkt=authour "+theBook.getLongIntro());
            bookList.add(theBook);
        }


    }


    //章节详情
    public static chapterDetailBean.Chapter handleChapterDetailResponse(String response){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonObject chapter=jsonObject.get("chapter").getAsJsonObject();
        String title=chapter.get("title").getAsString();
        String body=chapter.get("body").getAsString();
        chapterDetailBean.Chapter chapter1=new chapterDetailBean.Chapter(title,body);
        Log.d("Utility","rrrtitle="+title);
        return chapter1;
}



    //书籍详情
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
    public static BookDetail handleBookDetailResponse(String response,List<Book> booklist){
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        jsonObject.get("title").getAsString();
        Log.d("Utility","rrr1"+jsonObject.get("title").getAsString());
        //再转JsonArray 加上数据头
        BookDetail bookDetail=new Gson().fromJson(response,BookDetail.class);
        String bookDetailAddress="http://api.zhuishushenqi.com/mix-atoc/"+bookDetail.get_id()+"?view=chapters";
        Log.d("Utility","rrr"+bookDetail.getTitle());
        Book theBook=new Book(bookDetail.get_id(),bookDetail.getTitle(),bookDetail.getLastChapter(),
                null,bookDetail.getCover());
        booklist.add(theBook);
        return bookDetail;

    }








}
