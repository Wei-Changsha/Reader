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
         * @param url url的后面部分，具体实现是在后面的具体P层
         */
        void getClassifyListDetail(String url);
    }

    interface V {
        void showClassifyList(List<Bookbean> list);
    }
}
