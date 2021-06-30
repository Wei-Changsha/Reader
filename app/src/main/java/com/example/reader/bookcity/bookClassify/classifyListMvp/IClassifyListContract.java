package com.example.reader.bookcity.bookClassify.classifyListMvp;

import com.example.reader.bean.Bookbean;

import java.util.List;

public interface IClassifyListContract {
    interface M {
        void requestCListFromServer(String id);
    }

    interface V {
        void requestCList(String id);
        void responseCList(List<Bookbean> bookbeanList);
    }

    interface P {
        void requestCList(String id);
        void responseClist(String responseText);
    }
}
