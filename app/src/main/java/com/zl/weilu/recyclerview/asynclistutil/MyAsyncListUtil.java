package com.zl.weilu.recyclerview.asynclistutil;

import com.zl.weilu.recyclerview.bean.TestBean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/10/22 0022 16:37.
 */
public class MyAsyncListUtil extends AsyncListUtil<TestBean> {

    /**
     * 一次加载数据的个数，分页数量
     */
    private static final int TILE_SIZE = 20;

    public MyAsyncListUtil(RecyclerView mRecyclerView) {
        super(TestBean.class, TILE_SIZE, new MyDataCallback(), new MyViewCallback(mRecyclerView));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 更新当前可见范围数据
                onRangeChanged();
            }
        });
    }

    /**
     * 获取数据回调
     */
    public static class MyDataCallback extends DataCallback<TestBean>{

        /**
         * 总数据个数
         */
        @Override
        public int refreshData() {
            return 200;
        }

        /**
         * 填充数据（后台线程），一般为读取数据库数据
         */
        @Override
        public void fillData(@NonNull TestBean[] data, int startPosition, int itemCount) {
            for (int i = 0; i < itemCount; i++) {
                TestBean item = data[i];
                if (item == null) {
                    item = new TestBean(startPosition, "Item：" + (startPosition + i));
                    data[i] = item;
                }
            }

            try {
                // 模拟加载数据中
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用于获取可见项范围和更新通知的回调
     */
    public static class MyViewCallback extends ViewCallback {

        private RecyclerView mRecyclerView;

        public MyViewCallback(RecyclerView mRecyclerView) {
            this.mRecyclerView = mRecyclerView;
        }

        /**
         * 展示数据的范围
         */
        @Override
        public void getItemRangeInto(@NonNull int[] outRange) {
            RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
            LinearLayoutManager mgr = (LinearLayoutManager) manager;
            outRange[0] = mgr.findFirstVisibleItemPosition();
            outRange[1] = mgr.findLastVisibleItemPosition();
        }

        /**
         * 刷新数据
         */
        @Override
        public void onDataRefresh() {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

        /**
         * Item更新
         */
        @Override
        public void onItemLoaded(int position) {
            mRecyclerView.getAdapter().notifyItemChanged(position);
        }
    }
}
