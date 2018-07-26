package com.hrw.smartrecyclerviewlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    List<Integer> headerTypes = new ArrayList<>();
    List<Integer> footerTypes = new ArrayList<>();
    List<Integer> itemTypes = new ArrayList<>();
    Map<Integer, View> headerViews = new HashMap<>();
    Map<Integer, View> footerViews = new HashMap<>();
    Map<Integer, Integer> itemViews = new HashMap<>();
    int VIEW_CONTENT = 0x1000;
    boolean isInstanceOfBaseSmartBO = false;


    public SmartAdapter() {
        this(-1);
    }


    public SmartAdapter(@NonNull int layoutId) {
        this(null, layoutId);
    }

    public SmartAdapter(List<T> tList) {
        this(tList, -1);
    }

    public SmartAdapter(List<T> tList, @NonNull int layoutId) {
        this(tList, layoutId, null);
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

    public void setItemTypes(@NonNull Map<Integer, Integer> typeMap) {
        for (Map.Entry<Integer, Integer> entry : typeMap.entrySet()) {
            itemTypes.add(entry.getKey());
            itemViews.put(entry.getKey(), entry.getValue());
            isInstanceOfBaseSmartBO = true;
        }
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
        }
        if (isInstanceOfBaseSmartBO) {
            BaseSmartBO t = (BaseSmartBO) tList.get(position - headerViews.size());
            return t.getItemType();
        } else {
            return VIEW_CONTENT;
        }
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.containsKey(viewType)) {
            return new SmartVH(headerViews.get(viewType));
        } else if (footerViews.containsKey(viewType)) {
            return new SmartVH(footerViews.get(viewType));
        } else if (itemViews.containsKey(viewType)) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemViews.get(viewType), null);
            return new SmartVH(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new SmartVH(view);
        }

    }

    @Override
    public void onBindViewHolder(final SmartVH holder, final int position) {
        if (position >= headerViews.size() && position < (headerViews.size() + tList.size())) {
            bindView(holder, tList.get(getRealPosition(holder)), position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSmartItemClickListener != null) {
                        onSmartItemClickListener.onSmartItemClick(tList.get(getRealPosition(holder)), position);
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
