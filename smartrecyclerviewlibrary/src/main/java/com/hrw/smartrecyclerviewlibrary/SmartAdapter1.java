package com.hrw.smartrecyclerviewlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2017/09/17 14:56
 * @desc: 齐聚添加多头部、多底部、多item样式、下拉刷新动画、上了加载动画为一体的超级适配器
 */

public abstract class SmartAdapter1<T> extends BaseSmartAdapter<T> {
    OnSmartLoadMoreListener onSmartLoadMoreListener;
    OnSmartRefreshListener onSmartRefreshListener;
    List<View> headerViews = new ArrayList<>();
    List<View> footerViews = new ArrayList<>();
    View refreshView;
    View loadMoreView;


    public SmartAdapter1(@NonNull int layoutId) {
        super(layoutId);
    }

    public SmartAdapter1(List<T> tList, @NonNull int layoutId) {
        super(layoutId);
        this.tList = tList;
    }

    /**
     * 如果是GridLayoutManager布局的话，且有头部，则实现该构造方法
     *
     * @param layoutId
     * @param recyclerView
     */
    public SmartAdapter1(List<T> tList, int layoutId, RecyclerView recyclerView) {
        super(layoutId);
        this.tList = tList;

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isExistHeader() && isHeader(position) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }


    public void setHeaderView(View headerView) {
        headerViews.add(headerView);
    }

    public void setFooterView(View footerView) {
        footerViews.add(footerView);
    }

    public void setOnSmartLoadMoreListener(OnSmartLoadMoreListener onSmartLoadMoreListener) {
        this.onSmartLoadMoreListener = onSmartLoadMoreListener;
    }

    public void setOnSmartRefreshListener(OnSmartRefreshListener onSmartRefreshListener) {
        this.onSmartRefreshListener = onSmartRefreshListener;
    }

    public void setRefreshView(View refreshView) {
        this.refreshView = refreshView;
    }

    public void setLoadMoreView(View loadMoreView) {
        this.loadMoreView = loadMoreView;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == VIEW_HEADER) {
            return new SmartVH(headerView);
        } else {
            View view = View.inflate(parent.getContext(), layoutId, null);
            return new SmartVH(view);
        }
    }

    @Override
    public void onBindViewHolder(SmartVH holder, int position) {
        if (getItemViewType(position) == VIEW_HEADER) return;
        bindView(holder, tList.get(getRealPosition(holder)), position);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }


    @Override
    public int getItemCount() {
        return headerView == null ? tList.size() : tList.size() + 1;
    }


    public boolean isExistHeader() {
        return headerView != null;
    }

    public int getHeaderCount() {
        if (headerView != null) return 1;
        return 0;
    }


}