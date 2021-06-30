package com.example.reader.bookcity.bookClassify.detailRxjava;

import com.example.reader.bean.BookDetail;

public interface IdetailContractRx {
    interface P {
        /**
         * @param bookId 书籍ID
         */
        void getBookDetail(String bookId);//在P层调用，P层向M层发起请求

    }


    interface V {
        /**
         * @param bookDetail 从P层返回的书籍的详情
         */
        //P层调用，P层向View层发送请求
        void showBookDetail(BookDetail bookDetail);
    }
}
