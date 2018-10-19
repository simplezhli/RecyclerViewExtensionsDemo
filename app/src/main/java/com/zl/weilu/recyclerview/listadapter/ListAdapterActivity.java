package com.zl.weilu.recyclerview.listadapter;

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
 * @Time: 2018/10/19 0019 13:13.
 */
public class ListAdapterActivity extends AppCompatActivity {

    private MyListAdapter mMyListAdapter;
    private int count = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMyListAdapter = new MyListAdapter(this);
        mRecyclerView.setAdapter(mMyListAdapter);
        initData();
    }

    private void addData() {
        mMyListAdapter.setData(new TestBean(count, "Item " + count));
        count ++;
    }

    private List<TestBean> mList = new ArrayList();

    private void initData() {
        mList.clear();
        for (int i = 0; i < 10; i++){
            mList.add(new TestBean(i, "Item " + i));
        }
        mMyListAdapter.setData(mList);
    }

    private void updateData() {
        mList.clear();
        for (int i = 9; i >= 0; i--){
            mList.add(new TestBean(i, "Item " + i));
        }
        mMyListAdapter.setData(mList);
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
            if (mMyListAdapter.getItemCount() > 0){
                mMyListAdapter.removeData(mRandom.nextInt(mMyListAdapter.getItemCount()));
            }
        }else if (i == R.id.menu_clear){
            mMyListAdapter.clear();
        }
        return true;
    }
}
