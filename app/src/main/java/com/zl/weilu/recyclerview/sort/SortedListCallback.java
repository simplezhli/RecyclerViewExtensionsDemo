package com.zl.weilu.recyclerview.sort;

import com.zl.weilu.recyclerview.bean.City;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedListAdapterCallback;

public class SortedListCallback extends SortedListAdapterCallback<City> {
    
    public SortedListCallback(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    /**
     * 排序条件
     */
    @Override
    public int compare(City o1, City o2) {
        return o1.getFirstLetter().compareTo(o2.getFirstLetter());
    }

    /**
     * 用来判断两个对象是否是相同的Item。
     */
    @Override
    public boolean areItemsTheSame(City item1, City item2) {
        return item1.getId() == item2.getId();
    }
    
    /**
     * 用来判断两个对象是否是内容的Item。
     */
    @Override
    public boolean areContentsTheSame(City oldItem, City newItem) {
        if (oldItem.getId() != newItem.getId()) {
            return false;
        }
        return oldItem.getCityName().equals(newItem.getCityName());
    }
}
