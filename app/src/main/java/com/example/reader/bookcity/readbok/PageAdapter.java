package com.example.reader.bookcity.readbok;

import android.view.View;

public abstract class PageAdapter

{
    //return 页面view
    public abstract View getView();
    public abstract int getCount();

    //将内容添加到view中.  view：包含内容的view， position：第position页
    public abstract void addContent(View view, int position);

}
