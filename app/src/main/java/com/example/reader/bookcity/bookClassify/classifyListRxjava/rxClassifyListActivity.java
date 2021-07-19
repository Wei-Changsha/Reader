package com.example.reader.bookcity.bookClassify.classifyListRxjava;

import android.content.Intent;
import android.os.Bundle;
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

public class rxClassifyListActivity extends BaseActivity<rxClassifyListPresenter, IClassifyListContractRx.V> {

    private RecyclerView recyclerView;
    private TextView bookID;
    private TextView bookTitle;

    @Override
    public void findView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

//        bundle.putString("gender", "female");
//        bundle.putString("type", "hot");
//        bundle.putString("major", "现代言情");
//        bundle.putString("minor", "豪门总裁");
//        bundle.putInt("start", 0);
//        bundle.putInt("limit", 30);

        String gender = bundle.getString("gender");
        String type = bundle.getString("type");
        String major = bundle.getString("major");
        String minor = bundle.getString("minor");
        int start = bundle.getInt("start");
        int limit = bundle.getInt("limit");

        //在这里向P层发起请求
        //mPresenter.getContract().getClassifyListDetail(address);
        mPresenter.getContract().getClassifyListDetail(gender, type, major, minor, start, limit);

        bookID = findViewById(R.id.book_id);
        recyclerView = findViewById(R.id.recycler_view);
        bookTitle = findViewById(R.id.book_title);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_classify_list;
    }

    @Override
    public rxClassifyListPresenter getPresenterInstance() {
        return new rxClassifyListPresenter();
    }

    @Override
    public IClassifyListContractRx.V getContract() {
        return new IClassifyListContractRx.V() {
            @Override
            public void showClassifyList(final List<Bookbean> list) {
                //TODO  展示获取的列表
                initView(list);
            }
        };
    }

    private void initView(final List<Bookbean> bookbeanList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //layoutManager指定recycler view的布局方式为LinearLayout

        ClassifyListAdapter adapter = new ClassifyListAdapter(bookbeanList, this);
        //将list数据传到适配器中;
        recyclerView.setAdapter(adapter);

        //recyclerView点击的监听器  adapter.notifyItemChanged(0);
        adapter.setOnItemClickListener(new ClassifyListAdapter.OnItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int pos) {
                recyclerView.setVisibility(View.VISIBLE);
                Bookbean book = bookbeanList.get(pos);

                //String bookUrl="http://api.zhuishushenqi.com/book/"+bookID.getText().toString();
                String bookUrl = book.get_id();

                //跳转到书籍详情页
                Intent intent = new Intent(rxClassifyListActivity.this, rxDetailActivity.class);
                intent.putExtra("bookUrl", bookUrl);
                startActivity(intent);
            }

            @Override//长按点击事件
            public void onItemLongOnClick(View view, int pos) {

            }
        });

    }
}
