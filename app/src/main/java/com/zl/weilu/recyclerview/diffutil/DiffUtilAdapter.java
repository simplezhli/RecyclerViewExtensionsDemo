package com.zl.weilu.recyclerview.diffutil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DiffUtilAdapter extends RecyclerView.Adapter<DiffUtilAdapter.ViewHolder> {
   
    private List<TestBean> mList = new ArrayList();
    private LayoutInflater mInflater;
    private MyDiffUtilCallback mDiffCallback;
    
    public DiffUtilAdapter(Context mContext) {
        mDiffCallback = new MyDiffUtilCallback();
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(TestBean mData){
        mList.add(mData);
        notifyItemRangeInserted(getItemCount(), 1);
    }

    public void setData(List<TestBean> mData){
        mDiffCallback.setItems(mList, mData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(mDiffCallback);
        diffResult.dispatchUpdatesTo(this);
        mList.clear();
        mList.addAll(mData);
    }

    public void removeData(int index){
        mList.remove(index);
        notifyItemRemoved(index);
        if (index != mList.size()) {
            notifyItemRangeChanged(index, mList.size() - index);
        }
    }
    
    public void clear(){
        mList.clear();
        notifyDataSetChanged();
    }
    
    @Override
    @NonNull
    public DiffUtilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.mTvName.setText(bundle.getString("KEY_NAME"));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DiffUtilAdapter.ViewHolder holder, final int position) {
        TestBean bean = mList.get(position);
        holder.mTvName.setText(bean.getName());
    }
    
    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        
        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
