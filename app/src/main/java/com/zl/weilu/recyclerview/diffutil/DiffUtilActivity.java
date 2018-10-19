package com.zl.weilu.recyclerview.diffutil;

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
 * @Time: 2018/10/18 0018 16:56.
 */
public class DiffUtilActivity extends AppCompatActivity {

    private DiffUtilAdapter mDiffUtilAdapter;
    private int count = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDiffUtilAdapter = new DiffUtilAdapter(this);
        mRecyclerView.setAdapter(mDiffUtilAdapter);
        initData();
    }

    private void addData() {
        mDiffUtilAdapter.setData(new TestBean(count, "Item " + count));
        count ++;
    }

    private List<TestBean> mList = new ArrayList();

    private void initData() {
        mList.clear();
        for (int i = 0; i < 10; i++){
            mList.add(new TestBean(i, "Item " + i));
        }
        mDiffUtilAdapter.setData(mList);
    }

    private void updateData() {
        mList.clear();
        for (int i = 9; i >= 0; i--){
            mList.add(new TestBean(i, "Item " + i));
        }
        mDiffUtilAdapter.setData(mList);
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
            if (mDiffUtilAdapter.getItemCount() > 0){
                mDiffUtilAdapter.removeData(mRandom.nextInt(mDiffUtilAdapter.getItemCount()));
            }
        }else if (i == R.id.menu_clear){
            mDiffUtilAdapter.clear();
        }
        return true;
    }
}
