package com.hrw.smartrecyclerviewlibrary;

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

    public BaseSmartAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setDate(List<T> tList) {
        if (tList != null) {
            this.tList = tList;
            notifyDataSetChanged();
        }
    }

    public void addDate(List<T> tList) {
        if (tList != null) {
            int stPosition = this.tList.size() - 1;
            this.tList.addAll(tList);
            notifyItemChanged(stPosition, this.tList.size() - 1);
        }
    }

    @Override
    public SmartVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), layoutId, null);
        return new SmartVH(view);
    }

    @Override
    public void onBindViewHolder(SmartVH holder, final int position) {
        bindView(holder, tList.get(position), position);
    }

    protected abstract void bindView(SmartVH holder, T t, int position);


}
