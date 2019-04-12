package com.zl.weilu.recyclerview.precomputed;

import android.os.Bundle;

import com.zl.weilu.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 使用PrecomputedText提升列表的滑动帧率
 * @Author: weilu
 * @Time: 2019/04/12 0012 13:21.
 */
public class PrecomputedTextActivity extends AppCompatActivity {

    private PrecomputedTextAdapter mPrecomputedTextAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPrecomputedTextAdapter = new PrecomputedTextAdapter(this);
        mRecyclerView.setAdapter(mPrecomputedTextAdapter);
        initData();
    }

    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890.".toCharArray();
    
    private void initData() {
        List<String> mList = new ArrayList<>();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++){
            stringBuilder.delete(0, stringBuilder.length());
            for (int j = 0; j < 800; j++){
                stringBuilder.append(alphabet[random.nextInt(37)]);
            }
            mList.add(stringBuilder.toString());
        }

        mPrecomputedTextAdapter.setData(mList);
    }

}
