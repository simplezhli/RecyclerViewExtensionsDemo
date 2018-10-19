package com.zl.weilu.recyclerview.asynclistdiffer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.bean.TestBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: weilu
 * @Time: 2018/10/19 0019 11:33.
 */
public class AsyncListDifferActivity extends AppCompatActivity {

    private AsyncListDifferAdapter mAsyncListDifferAdapter;
    private int count = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAsyncListDifferAdapter = new AsyncListDifferAdapter(this);
        mRecyclerView.setAdapter(mAsyncListDifferAdapter);
        initData();
    }

    private void addData() {
        mAsyncListDifferAdapter.setData(new TestBean(count, "Item " + count));
        count ++;
    }

    private List<TestBean> mList = new ArrayList();

    private void initData() {
        mList.clear();
        for (int i = 0; i < 10; i++){
            mList.add(new TestBean(i, "Item " + i));
        }
        mAsyncListDifferAdapter.setData(mList);
    }

    private void updateData() {
        mList.clear();
        for (int i = 9; i >= 0; i--){
            mList.add(new TestBean(i, "Item " + i));
        }
        mAsyncListDifferAdapter.setData(mList);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    private Random mRandom = new Random();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_add) {
            addData();
        } else if (i == R.id.menu_update) {
            updateData();
        } else if (i == R.id.menu_delete) {
            if (mAsyncListDifferAdapter.getItemCount() > 0){
                mAsyncListDifferAdapter.removeData(mRandom.nextInt(mAsyncListDifferAdapter.getItemCount()));
            }
        }else if (i == R.id.menu_clear){
            mAsyncListDifferAdapter.clear();
        }
        return true;
    }
}
