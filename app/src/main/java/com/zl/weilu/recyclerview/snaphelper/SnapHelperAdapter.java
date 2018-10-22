package com.zl.weilu.recyclerview.snaphelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: weilu
 * @Time: 2018/10/22 0022 13:07.
 */
public class SnapHelperAdapter extends RecyclerView.Adapter<SnapHelperAdapter.ViewHolder> {
   
    private LayoutInflater mInflater;
    private List<TestBean> mData = new ArrayList<>();
    
    public SnapHelperAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
        for (int i = 0; i < 20; i++){
            mData.add(new TestBean(i, "Item：" + i));
        }
    }

    @Override
    @NonNull
    public SnapHelperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_snap_helper, parent, false));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SnapHelperAdapter.ViewHolder holder, final int position) {
        TestBean bean = mData.get(position);
        holder.mTvName.setText(bean.getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        
        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
