package com.zl.weilu.recyclerview.asynclistdiffer;

import android.os.Bundle;

import com.zl.weilu.recyclerview.bean.TestBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/10/19 0019 13:18.
 */
public class MyDiffUtilItemCallback extends DiffUtil.ItemCallback<TestBean> {

    @Override
    public boolean areItemsTheSame(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();

        if (!oldItem.getName().equals(newItem.getName())) {
            payload.putString("KEY_NAME", newItem.getName());
        }

        if (payload.size() == 0){
            //如果没有变化 就传空
            return null;
        }
        return payload;
    }
}

