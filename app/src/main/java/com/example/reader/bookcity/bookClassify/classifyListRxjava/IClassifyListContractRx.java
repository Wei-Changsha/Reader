package com.example.reader.bookcity.bookClassify.classifyListRxjava;

import com.example.reader.bean.Bookbean;

import java.util.List;

/**
 * @author wei
 * 获取每一个分类目录下的书籍列表的  契约结构
 */
public interface IClassifyListContractRx {
    interface P {
        /**
         * 具体实现是在后面的具体P层
         * 获取具体摸一个分类下的书籍列表
         * @param url 的后面部分
         */
        void getClassifyListDetail(String gender,String type, String major, String minor, int start, int limit);
    }

    interface V {
        /**
         * 在V层显示获取到的分类下的书籍列表
         * @param list  从P层获取的书籍列表
         */
        void showClassifyList(List<Bookbean> list);
    }
}
