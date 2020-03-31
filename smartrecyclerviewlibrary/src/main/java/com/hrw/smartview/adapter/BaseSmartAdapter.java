package com.hrw.smartview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2017/10/10 21:06
 * @desc:
 */

public abstract class BaseSmartAdapter<T> extends RecyclerView.Adapter<SmartVH> {
    protected List<T> tList = new ArrayList<>();
    protected int layoutId;
    protected Context mContent;

    public BaseSmartAdapter() {
    }

    public BaseSmartAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    public BaseSmartAdapter(List<T> tList, @NonNull int layoutId) {
        this.tList = tList;
        this.layoutId = layoutId;
    }

    public BaseSmartAdapter(T[] dates, int layoutId) {
        List<T> tList = new ArrayList<>();
        for (T t : dates) {
            tList.add(t);
        }
        this.tList = tList;
        this.layoutId = layoutId;
    }

    public void setDate(List<T> tList) {
        if (tList != null) {
            this.tList = tList;
            notifyDataSetChanged();
        }
    }

    public void addData(T data) {
        if (tList != null) {
            int stPosition = this.tList.size() - 1;
            addData(stPosition, data);
        }
    }

    public void addData(int position, T data) {
        if (tList != null && position >= 0) {
            this.tList.add(position, data);
            notifyItemRangeInserted(position, 1);
        }
    }

    public void addDates(List<T> dates) {
        if (tList != null) {
            int stPosition = this.tList.size() - 1;
            addDates(stPosition, dates);
        }
    }

    public void addDates(int position, List<T> dates) {
        if (tList != null && position >= 0) {
            this.tList.addAll(position, dates);
            notifyItemRangeInserted(position, dates.size());
        }
    }

    public void removeData(int position) {
        if (tList != null) {
            this.tList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeDates(int startPosition, int endPosition) {
        if (tList != null) {
            int removeCount = 0;
            for (int i = 0; i < tList.size(); i++) {
                if (i == startPosition && removeCount == endPosition - startPosition) {
                    this.tList.remove(i);
                    removeCount++;
                }
            }
            notifyItemMoved(startPosition, endPosition);
        }
    }


    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContent = parent.getContext();
        View view = View.inflate(parent.getContext(), layoutId, null);
        return new SmartVH(view);
    }

    @Override
    public void onBindViewHolder(SmartVH holder, final int position) {
        bindView(holder, tList.get(position), position);
    }

    protected abstract void bindView(SmartVH holder, T t, int position);


}
