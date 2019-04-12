package com.zl.weilu.recyclerview.precomputed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.weilu.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PrecomputedTextAdapter extends RecyclerView.Adapter<PrecomputedTextAdapter.ViewHolder> {
   
    private LayoutInflater mInflater;
    private List<String> mData = new ArrayList<>();
    
    public PrecomputedTextAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<String> list){
        mData.addAll(list);
        notifyDataSetChanged();
    }
    
    @Override
    @NonNull
    public PrecomputedTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_precomputed_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrecomputedTextAdapter.ViewHolder holder, final int position) {
        float size = 10f;
        if (position % 2 == 0){
            size = 12f;
        }
        if (position % 3 == 0){
            size = 14f;
        }
        if (position % 5 == 0){
            size = 16f;
        }
        holder.mTvName.setTextSize(size);
//        holder.mTvName.setText(mData.get(position));
        holder.mTvName.setTextFuture(PrecomputedTextCompat.getTextFuture(mData.get(position),
                TextViewCompat.getTextMetricsParams(holder.mTvName), null));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView mTvName;
        
        ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
