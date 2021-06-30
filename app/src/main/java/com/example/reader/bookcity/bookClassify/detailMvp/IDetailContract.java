package com.example.reader.bookcity.bookClassify.detailMvp;

import com.example.reader.bean.BookDetail;

public interface IDetailContract {
    interface P {
        void requestBookDetail(String bookId);//在P层调用，P层向M层发起请求
        void responseBookDetail(String responseText);
        //在M层获取Presenter调用，M层向P层传递访问服务器的返回信息
    }

    interface M {
        void requestBookDetailFromServer(String bookId);
        //在M层调用， 访问服务器
    }

    interface V {
        //v层调用，向P层发送请求
        void requestBookDetail(String bookId);
        //P层调用，P层向View层发送请求
        void responseBookDetail(BookDetail bookDetail);
    }

}
