package com.example.reader.bookcity.readbok;


import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.reader.R;

import java.util.List;


public class ScanViewAdapter extends PageAdapter
{
    Context context;
    List<String> items;
    AssetManager am;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ScanViewAdapter(Context context, List<String> items,String body)
    {
        this.context = context;
        this.items = items;
        am = context.getAssets();
        this.body=body;
    }

    public void addContent(View view, int position)
    {
        TextView content = view.findViewById(R.id.content);
        TextView tv =  view.findViewById(R.id.index);
        if ((position - 1) < 0 || (position - 1) >= getCount()) {
            return;
        }

        content.setText(body);
        tv.setText(items.get(position - 1));
    }

    public int getCount()
    {
        return items.size();
    }

    public View getView()
    {
        View view = LayoutInflater.from(context).inflate(R.layout.read_book,
                null);
        return view;
    }


}