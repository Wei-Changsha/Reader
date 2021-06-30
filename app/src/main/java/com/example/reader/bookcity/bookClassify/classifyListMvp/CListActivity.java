package com.example.reader.bookcity.bookClassify.classifyListMvp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reader.R;
import com.example.reader.base.BaseActivity;
import com.example.reader.bean.Bookbean;
import com.example.reader.bookcity.bookClassify.ClassifyListAdapter;
import com.example.reader.bookcity.bookClassify.detailRxjava.rxDetailActivity;

import java.util.List;

public class CListActivity extends BaseActivity<ClistPresenter,IClassifyListContract.V> {

    private RecyclerView recyclerView;
    private TextView bookID;
    private TextView bookTitle;

    @Override
    public void findView() {
        Intent intent=getIntent();
        String address=intent.getStringExtra("address");
        getContract().requestCList(address);

        recyclerView = findViewById(R.id.recycler_view);

        bookTitle = findViewById(R.id.book_title);
    }

    private void initView(List<Bookbean> bookbeanList) {
        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);//layoutManager指定recycler view的布局方式为LinearLayout
        final ClassifyListAdapter adapter=new ClassifyListAdapter(bookbeanList,this);//将list数据传到适配器中;
        recyclerView.setAdapter(adapter);


        //recyclerView点击的监听器
        //adapter.notifyItemChanged(0);
        adapter.setOnItemClickListener(new ClassifyListAdapter.OnItemOnClickListener() {
            @Override//短暂点击事件
            public void onItemOnClick(View view, int pos) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter.getViewHolderMap().get(pos);
                bookID = findViewById(R.id.book_id);
                //String bookUrl="http://api.zhuishushenqi.com/book/"+bookID.getText().toString();
                String bookUrl = bookID.getText().toString();
                Log.d("CList Avtivity ccc01 bookiddd = " ,bookUrl);
                //跳转到书籍详情页
                //Intent intent=new Intent(CListActivity.this, MVPDetailActivity.class);
                Intent intent=new Intent(CListActivity.this, rxDetailActivity.class);
                intent.putExtra("bookUrl",bookUrl);
                startActivity(intent);

            }

            @Override//长按点击事件
            public void onItemLongOnClick(View view, int pos) {

            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_classify_list;
    }

    @Override
    public ClistPresenter getPresenterInstance() {
        return new ClistPresenter();
    }

    @Override
    public IClassifyListContract.V getContract() {
        return new IClassifyListContract.V() {
            @Override
            public void requestCList(String id) {
                mPresenter.getContract().requestCList(id);
            }

            @Override
            public void responseCList(final List<Bookbean> bookbeanList) {
                //更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView(bookbeanList);
                    }
                });

            }
        };
    }
}
