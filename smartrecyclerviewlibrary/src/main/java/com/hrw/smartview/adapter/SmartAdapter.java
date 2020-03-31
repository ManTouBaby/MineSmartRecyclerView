package com.hrw.smartview.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrw.smartview.listener.OnSmartItemChildClickListener;
import com.hrw.smartview.listener.OnSmartItemClickListener;
import com.hrw.smartview.listener.OnSmartItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2017/09/17 14:56
 * @desc: 齐聚添加多头部、多底部、多item样式、下拉刷新动画、上了加载动画为一体的超级适配器
 */

public abstract class SmartAdapter<T> extends BaseSmartAdapter<T> {
    private List<Integer> headerTypes = new ArrayList<>();
    private List<Integer> footerTypes = new ArrayList<>();
    private SparseArray<View> headerViews = new SparseArray<>();
    private SparseArray<View> footerViews = new SparseArray<>();
    private SparseIntArray itemViews;
    private int VIEW_CONTENT = 0x1000;
    private boolean isUseTypeItem = false;


    public SmartAdapter(@NonNull int layoutId) {
        super(layoutId);
    }

    public SmartAdapter(List<T> tList, @NonNull int layoutId) {
        super(layoutId);
        this.tList = tList;
    }

    public SmartAdapter(SparseIntArray itemViews) {
        isUseTypeItem = true;
        this.itemViews = itemViews;
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
        itemViews = new SparseIntArray();
        if (itemViews.get(itemType, -1) == -1) itemViews.put(itemType, layoutRes);
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
        if (headerViews.get(viewType, null) != null) {
            return new SmartVH(headerViews.get(viewType));
        } else if (footerViews.get(viewType, null) != null) {
            return new SmartVH(footerViews.get(viewType));
        } else if (itemViews.get(viewType, -1) != -1) {
            return new SmartVH(LayoutInflater.from(parent.getContext()).inflate(itemViews.get(viewType), parent, false));
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new SmartVH(view);
        }

    }

    @Override
    public void onBindViewHolder(final SmartVH holder, int position) {
        if (position >= headerViews.size() && position < (headerViews.size() + tList.size())) {
            bindView(holder, tList.get(getRealPosition(holder)), getRealPosition(holder));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSmartItemClickListener != null) {
                        onSmartItemClickListener.onSmartItemClick(tList.get(getRealPosition(holder)), getRealPosition(holder));
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onSmartItemLongClickListener != null) {
                        onSmartItemLongClickListener.onSmartItemLongClick(tList.get(getRealPosition(holder)), getRealPosition(holder));
                    }
                    return false;
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

    public void addChildClickListener(final View view, final T o, final int position) {
        if (view != null && onSmartItemChildClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSmartItemChildClickListener.onSmartItemClick(view, o, position);
                }
            });
        }
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

    private OnSmartItemClickListener onSmartItemClickListener;
    private OnSmartItemLongClickListener onSmartItemLongClickListener;
    private OnSmartItemChildClickListener onSmartItemChildClickListener;

    public void setOnSmartItemClickListener(OnSmartItemClickListener<T> onSmartItemClickListener) {
        this.onSmartItemClickListener = onSmartItemClickListener;
    }

    public void setOnSmartItemLongClickListener(OnSmartItemLongClickListener<T> onSmartItemLongClickListener) {
        this.onSmartItemLongClickListener = onSmartItemLongClickListener;
    }

    public void setOnSmartItemChildClickListener(OnSmartItemChildClickListener<T> smartItemChildClickListener) {
        this.onSmartItemChildClickListener = smartItemChildClickListener;
    }
}
