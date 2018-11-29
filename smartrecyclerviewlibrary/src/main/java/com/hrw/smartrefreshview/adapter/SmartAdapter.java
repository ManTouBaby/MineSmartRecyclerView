package com.hrw.smartrefreshview.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrw.smartrefreshview.listener.OnSmartItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:MtBaby
 * @date:2017/09/17 14:56
 * @desc: 齐聚添加多头部、多底部、多item样式、下拉刷新动画、上了加载动画为一体的超级适配器
 */

public abstract class SmartAdapter<T> extends BaseSmartAdapter<T> {
    //    List<View> headerViews = new ArrayList<>();
//    List<View> footerViews = new ArrayList<>();
//    List<View> itemViews = new ArrayList<>();
    List<Integer> headerTypes = new ArrayList<>();
    List<Integer> footerTypes = new ArrayList<>();
    Map<Integer, View> headerViews = new HashMap<>();
    Map<Integer, View> footerViews = new HashMap<>();
    Map<Integer, Integer> itemViews = new HashMap<>();
    int VIEW_CONTENT = 0x1000;
    boolean isUseTypeItem = false;


    public SmartAdapter(@NonNull int layoutId) {
        super(layoutId);
    }

    public SmartAdapter(List<T> tList, @NonNull int layoutId) {
        super(layoutId);
        this.tList = tList;
    }

    /**
     * 如果是GridLayoutManager布局的话，且有头部，则实现该构造方法
     *
     * @param layoutId
     * @param recyclerView
     */
    public SmartAdapter(List<T> tList, int layoutId, RecyclerView recyclerView) {
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


    public void setItemType(@IntRange(from = 0, to = 100) int itemType, @LayoutRes int layoutRes) {
        isUseTypeItem = true;
        if (!itemViews.containsKey(itemType)) itemViews.put(itemType, layoutRes);
    }

    public void setHeaderView(View... headerView) {
        for (View view : headerView) {
            headerTypes.add(200 + headerViews.size());
            headerViews.put(200 + headerViews.size(), view);
        }
    }

    public void setFooterView(View... footerView) {
        for (View view : footerView) {
            footerTypes.add(300 + footerViews.size());
            footerViews.put(300 + footerViews.size(), view);
        }
    }


    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headerViews.size()) {
            return headerTypes.get(position);
        } else if (position >= headerViews.size() + tList.size()) {
            return footerTypes.get(position - (headerViews.size() + tList.size()));
        } else {
            T t = tList.get(position - headerViews.size());
            if (isUseTypeItem && t instanceof BaseSmartBO) {
                return ((BaseSmartBO) t).getItemType();
            } else {
                return VIEW_CONTENT;
            }
        }
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.containsKey(viewType)) {
            return new SmartVH(headerViews.get(viewType));
        } else if (footerViews.containsKey(viewType)) {
            return new SmartVH(footerViews.get(viewType));
        } else if (itemViews.containsKey(viewType)) {
            return new SmartVH(LayoutInflater.from(parent.getContext()).inflate(itemViews.get(viewType), parent, false));
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new SmartVH(view);
        }

    }

    @Override
    public void onBindViewHolder(SmartVH holder, final int position) {
        if (position >= headerViews.size() && position < (headerViews.size() + tList.size())) {
            bindView(holder, tList.get(getRealPosition(holder)), position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSmartItemClickListener != null) {
                        onSmartItemClickListener.onSmartItemClick(tList.get(position), position);
                    }
                }
            });
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerViews.size() > 0 ? position - headerViews.size() : position;
    }


    @Override
    public int getItemCount() {
        int itemCount = tList.size();
        if (isExistHeader()) itemCount += getHeaderCount();
        if (isExistFooter()) itemCount += getFooterCount();
        return itemCount;
    }


    public boolean isExistHeader() {
        return headerViews.size() > 0;
    }

    public int getHeaderCount() {
        return headerViews.size();
    }

    public boolean isExistFooter() {
        return footerViews.size() > 0;
    }

    public int getFooterCount() {
        return footerViews.size();
    }

    OnSmartItemClickListener onSmartItemClickListener;

    public void setOnSmartItemClickListener(OnSmartItemClickListener<T> onSmartItemClickListener) {
        this.onSmartItemClickListener = onSmartItemClickListener;
    }
}
