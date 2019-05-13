package com.zl.weilu.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zl.weilu.recyclerview.asynclistdiffer.AsyncListDifferActivity;
import com.zl.weilu.recyclerview.asynclistutil.AsyncListUtilActivity;
import com.zl.weilu.recyclerview.diffutil.DiffUtilActivity;
import com.zl.weilu.recyclerview.listadapter.ListAdapterActivity;
import com.zl.weilu.recyclerview.litho.LithoActivity;
import com.zl.weilu.recyclerview.precomputed.PrecomputedTextActivity;
import com.zl.weilu.recyclerview.snaphelper.SnapHelperActivity;
import com.zl.weilu.recyclerview.sort.SortedListActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void toSortedListActivity(View view){
        startActivity(new Intent(this, SortedListActivity.class));
    }

    public void toDiffUtilActivity(View view){
        startActivity(new Intent(this, DiffUtilActivity.class));
    }

    public void toAsyncListDifferActivity(View view){
        startActivity(new Intent(this, AsyncListDifferActivity.class));
    }

    public void toListAdapterActivity(View view){
        startActivity(new Intent(this, ListAdapterActivity.class));
    }

    public void toSnapHelperActivity(View view){
        startActivity(new Intent(this, SnapHelperActivity.class));
    }

    public void toAsyncListUtilActivity(View view){
        startActivity(new Intent(this, AsyncListUtilActivity.class));
    }

    public void toPrecomputedTextActivity(View view){
        startActivity(new Intent(this, PrecomputedTextActivity.class));
    }
    
    public void toLithoActivity(View view) { startActivity(new Intent(this, LithoActivity.class)); }
}

