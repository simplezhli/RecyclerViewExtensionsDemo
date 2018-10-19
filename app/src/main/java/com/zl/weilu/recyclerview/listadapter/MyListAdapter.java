package com.zl.weilu.recyclerview.listadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.weilu.recyclerview.R;
import com.zl.weilu.recyclerview.asynclistdiffer.MyDiffUtilItemCallback;
import com.zl.weilu.recyclerview.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapter extends ListAdapter<TestBean, MyListAdapter.ViewHolder> {
   
    private LayoutInflater mInflater;
    private List<TestBean> mData = new ArrayList<>();
    
    public MyListAdapter(Context mContext) {
        super(new MyDiffUtilItemCallback());
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(TestBean testBean){
        mData.add(testBean);
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mData);
        submitList(mList);
    }

    public void setData(List<TestBean> list){
        mData.clear();
        mData.addAll(list);
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mData);
        submitList(mList);
    }

    public void removeData(int index){
        mData.remove(index);
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mData);
        submitList(mList);
    }

    public void clear(){
        mData.clear();
        submitList(null);
    }
    
    @Override
    @NonNull
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, final int position) {
        TestBean bean = getItem(position);
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
