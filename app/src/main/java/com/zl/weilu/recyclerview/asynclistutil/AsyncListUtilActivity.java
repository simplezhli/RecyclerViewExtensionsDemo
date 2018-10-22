package com.zl.weilu.recyclerview.asynclistutil;

import android.os.Bundle;

import com.zl.weilu.recyclerview.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: weilu
 * @Time: 2018/10/22 0022 15:38.
 */
public class AsyncListUtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        MyAsyncListUtil myAsyncListUtil = new MyAsyncListUtil(mRecyclerView);
        AsyncListUtilAdapter mAdapter = new AsyncListUtilAdapter(this, myAsyncListUtil);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        
    }
}
