package com.zl.weilu.recyclerview.snaphelper;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Description: 左对齐的SnapHelper
 * @Author: weilu
 * @Time: 2018/10/22 0022 14:11.
 */
public class MySnapHelper extends LinearSnapHelper{

    /**
     * 水平、垂直方向的度量
     */
    @Nullable
    private OrientationHelper mVerticalHelper;
    @Nullable
    private OrientationHelper mHorizontalHelper;

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
    
    /**
     * 计算出View对齐到指定位置，所需的x、y轴上的偏移量。
     */
    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        
        // 水平方向滑动时计算x方向，否则偏移为0
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(layoutManager, targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        // 垂直方向滑动时计算y方向，否则偏移为0
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(layoutManager, targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    private int distanceToStart(RecyclerView.LayoutManager layoutManager, View targetView, OrientationHelper helper) {
        // RecyclerView的边界x值,也就是左侧Padding值
        final int start;
        if (layoutManager.getClipToPadding()) {
            start = helper.getStartAfterPadding();
        } else {
            start = 0;
        }
        return helper.getDecoratedStart(targetView) - start;
    }

    /**
     * 查找需要对齐的View
     */
    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findStartView(layoutManager, getVerticalHelper(layoutManager));
        } else {
            return findStartView(layoutManager, getHorizontalHelper(layoutManager));
        }
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        final int start;
        if (layoutManager.getClipToPadding()) {
            start = helper.getStartAfterPadding();
        } else {
            start = 0;
        }
        int absClosest = Integer.MAX_VALUE;
        
        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            // ItemView 的左侧坐标
            int childStart = helper.getDecoratedStart(child);
            // 计算此ItemView 与 RecyclerView左侧的距离
            int absDistance = Math.abs(childStart - start);

            // 找到那个最靠近左侧的ItemView然后返回
            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        
        return closestChild;
    }

    /**
     * 找到需要对齐的View的position，主要用于fling 操作
     */
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        // 左对齐和居中对齐一样，无需自定义处理
        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
    }
}
