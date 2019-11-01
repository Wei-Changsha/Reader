package com.example.reader;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.bean.Book;
import com.example.reader.bookcity.util.HttpUtil;
import com.example.reader.bookcity.util.Utility;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookShelfFragment extends Fragment implements Serializable {
    private static final String ARG_SECTION = "section";
    private List<Book> bookList = new ArrayList<>();//recyclerview数组
    //private List<BookDetail> bookDetailList=new ArrayList<>();

    static String title;

    public BookShelfFragment() {

    }

    //实例化碎片的构造方法
    public static BookShelfFragment newInstance(String setion) {
        BookShelfFragment fragment = new BookShelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION, setion);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_shelf, container, false);

        initBook();
        //Log.d("BookShelfFragment", "zzz"+bookList.size());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_shelf);//获取RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout


        //创建adapter
        BookShelfAdapter adapter = new BookShelfAdapter(bookList, getActivity());//将list数据传到适配器中;

        Log.d("BookShelfFragment", "zzz"+bookList.size());
        recyclerView.setAdapter(adapter);//给RecyclerView设置adapter
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BookShelfAdapter.OnItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int pos) {
                Book book = bookList.get(pos);

                Toast.makeText(getActivity(), book.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongOnClick(View view, int pos) {

            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // Log.d("BookShelfFragment", "fff=" + title);

    }


    public  void initBook() {

        List<Book> books= DataSupport.findAll(Book.class);
        Log.d("BookShelfFragment", "fff000=" + books.size());
        for (Book bookDetail1:books){
            Log.d("BookShelfFragment", "fff000=" + bookDetail1.getTitle());
            bookList.add(bookDetail1);
        }

    }


    public void queryFromServer(String address) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        //Toast.makeText(BookShelfFragment.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseText = response.body().string();
                Utility.handleBookDetailResponse(responseText, bookList);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        final RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout

                        BookShelfAdapter adapter = new BookShelfAdapter(bookList,getActivity());
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BookShelfAdapter.OnItemOnClickListener() {
                            @Override
                            public void onItemOnClick(View view, int pos) {

                            }

                            @Override
                            public void onItemLongOnClick(View view, int pos) {

                            }
                        });
                    }
                });
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
