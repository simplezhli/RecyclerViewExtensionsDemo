package com.zl.weilu.recyclerview.asynclistdiffer;

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
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

public class AsyncListDifferAdapter extends RecyclerView.Adapter<AsyncListDifferAdapter.ViewHolder> {
   
    private LayoutInflater mInflater;
    private AsyncListDiffer<TestBean> mDiffer;
    
    public AsyncListDifferAdapter(Context mContext) {
        mDiffer = new AsyncListDiffer<>(this, new MyDiffUtilItemCallback());
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(TestBean mData){
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.add(mData);
        mDiffer.submitList(mList);
    }

    public void setData(List<TestBean> mData){
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mData);
        mDiffer.submitList(mList);
    }

    public void removeData(int index){
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.remove(index);
        mDiffer.submitList(mList);
    }
    
    public void clear(){
        mDiffer.submitList(null);
    }
    
    @Override
    @NonNull
    public AsyncListDifferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull AsyncListDifferAdapter.ViewHolder holder, final int position) {
        TestBean bean = mDiffer.getCurrentList().get(position);
        holder.mTvName.setText(bean.getName());
    }
    
    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        
        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
