package com.example.reader.bookcity.bookClassify.detailMVVM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.reader.databinding.ActivityBookDetailBinding;

/**
 * @author weichangsha
 */
public class DetailMVVMActivity extends AppCompatActivity {


    private ActivityBookDetailBinding binding;

    public DetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件
        binding = ActivityBookDetailBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        //初始化viewModel
//        mViewModel = new ViewModelProvider(this,
//                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DetailViewModel.class);

       // mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DetailViewModel.class);

        mViewModel =  ViewModelProviders.of(this).get(DetailViewModel.class);
        //mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(DetailViewModel.class);

        //mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(DetailViewModel.class);

        mViewModel.setRepository(new DetailRepository());
        initView();

        Log.d("MVVM", " mvvm detail");

        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookUrl");
        mViewModel.getBookDetailById(bookId);
    }

    private void initView() {
        mViewModel.authorLiveData.observe(this, author ->
                binding.detailAuthor.setText(author));
        mViewModel.bookNameLivedata.observe(this, name ->
                binding.detailTitle.setText(name));
        mViewModel.infoLivaData.observe(this, info ->
                binding.detailLongInfo.setText(info));
        mViewModel.lastUpdateChaLivaData.observe(this, cha ->
                binding.detailLastChapter.setText(cha));
        mViewModel.lastUpdateTimeLivedata.observe(this, time ->
                binding.detailUpdated.setText(time));
        mViewModel.totalChaLiveData.observe(this, chas ->
                binding.detailChapterCount.setText(chas));
        mViewModel.totalWordsCountLiveData.observe(this, words ->
                binding.detailWordCount.setText(words));

    }


    private  ViewModelProvider.Factory getViewModelFactory() {
        return new ViewModelProvider.AndroidViewModelFactory(getApplication());
    }
    
}