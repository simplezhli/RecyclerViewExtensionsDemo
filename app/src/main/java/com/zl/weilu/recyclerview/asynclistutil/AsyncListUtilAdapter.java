package com.zl.weilu.recyclerview.asynclistutil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.bean.TestBean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: weilu
 * @Time: 2018/10/22 0022 15:38.
 */
public class AsyncListUtilAdapter extends RecyclerView.Adapter<AsyncListUtilAdapter.ViewHolder> {
   
    private LayoutInflater mInflater;
    private MyAsyncListUtil mMyAsyncListUtil;
    
    public AsyncListUtilAdapter(Context mContext, MyAsyncListUtil mMyAsyncListUtil) {
        this.mMyAsyncListUtil = mMyAsyncListUtil;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    @NonNull
    public AsyncListUtilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public int getItemCount() {
        return mMyAsyncListUtil.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull AsyncListUtilAdapter.ViewHolder holder, final int position) {
        TestBean bean = mMyAsyncListUtil.getItem(position);
        // 有可能获取为空，这是可以显示加载中，等待同步数据。
        if (bean == null){
            holder.mTvName.setText("加载中...");
        }else {
            holder.mTvName.setText(bean.getName());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        
        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
