package com.example.reader;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //private ViewAdapter adapter;
    //private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewPager与Fragment的绑定（3行代码）
        //初始化适配器,使用适配器将ViewPager与Fragment绑定在一起（在适配器里面已经初始化过Fragment了）
        final ViewPager viewPager = findViewById(R.id.Vp_findFragment);
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //给viewPaper设置事件监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滚动事件
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //页面选择事件
            @Override
            public void onPageSelected(int position) {
                //设置对应集合中的Fragment
                viewPager.setCurrentItem(position);
            }

            //页面滚动状态改变事件
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //将TabLayout与ViewPager绑定在一起
        //调用setupWithViewPager()方法，则使用TabLayout.addtab()方法无效，TabLayout会清除之前添加的所有tabs，
        //并将根据Viewpager的页数添加Tab，Tab标题为对应页通过getPageTitle()返回的文本
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.book_review:
                return false;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }


}
