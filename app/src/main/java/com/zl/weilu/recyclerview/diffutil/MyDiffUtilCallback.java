package com.zl.weilu.recyclerview.diffutil;

import android.os.Bundle;

import com.zl.weilu.recyclerview.bean.TestBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/10/18 0018 17:27.
 */
public class MyDiffUtilCallback extends DiffUtil.Callback{

    private List<TestBean> mOldItems;
    private List<TestBean> mNewItems;

    public void setItems(@NonNull final List<TestBean> oldItems, @NonNull final List<TestBean> newItems) {
        mOldItems = oldItems;
        mNewItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return mOldItems == null ? 0 : mOldItems.size();
    }

    @Override
    public int getNewListSize() {
        return mNewItems == null ? 0 : mNewItems.size();
    }

    /**
     * 是否是同一个对象
     */
    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        if (mOldItems.get(oldItemPosition) == null || mNewItems.get(newItemPosition) == null){
            return false;
        }
        return mOldItems.get(oldItemPosition).getId() == mNewItems.get(newItemPosition).getId();
    }
    
    /**
     * 是否是相同内容
     */
    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldItems.get(oldItemPosition).getName().equals(mNewItems.get(newItemPosition).getName());
    }

    /**
     * areItemsTheSame()返回true而areContentsTheSame()返回false时调用,也就是说两个对象代表的数据是一条，但是内容更新了。
     */
    @Nullable
    @Override
    public Object getChangePayload(final int oldItemPosition, final int newItemPosition) {
        TestBean oldBean = mOldItems.get(oldItemPosition);
        TestBean newBean = mNewItems.get(newItemPosition);

        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();
       
        if (!oldBean.getName().equals(newBean.getName())) {
            payload.putString("KEY_NAME", newBean.getName());
        }

        if (payload.size() == 0){
            //如果没有变化 就传空
            return null;
        }
        return payload;
    }
   
}