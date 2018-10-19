package com.zl.weilu.recyclerview.sort;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.bean.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

/**
 * @Description: 数据排序
 * @Author: weilu
 * @Time: 2018/10/18 0018 10:48.
 */
public class SortedListActivity extends AppCompatActivity {

    private SortedAdapter mSortedAdapter;
    private int count = 10;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSortedAdapter = new SortedAdapter(this);
        // SortedList初始化
        SortedListCallback mSortedListCallback = new SortedListCallback(mSortedAdapter);
        SortedList mSortedList = new SortedList<>(City.class, mSortedListCallback);
        
        mSortedAdapter.setSortedList(mSortedList);
        mRecyclerView.setAdapter(mSortedAdapter);
        updateData();
    }

    private void addData() {
        mSortedAdapter.setData(new City(count, "城市 " + count, "c"));
        count ++;
    }

    private List<City> mList = new ArrayList();

    // 自动去重
    private void updateData() {
        mList.clear();
        mList.add(new City(0, "北京", "b"));
        mList.add(new City(1, "上海", "s"));
        mList.add(new City(2, "广州", "g"));
        mList.add(new City(3, "深圳", "s"));
        mList.add(new City(4, "杭州", "h"));
        mList.add(new City(5, "西安", "x"));
        mList.add(new City(6, "成都", "c"));
        mList.add(new City(7, "武汉", "w"));
        mList.add(new City(8, "南京", "n"));
        mList.add(new City(9, "重庆", "c"));
        mSortedAdapter.setData(mList);
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
            if (mSortedAdapter.getItemCount() > 0){
                mSortedAdapter.removeData(mRandom.nextInt(mSortedAdapter.getItemCount()));
            }
        }else if (i == R.id.menu_clear){
            mSortedAdapter.clear();
        }
        return true;
    }
}
