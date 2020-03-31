package com.hrw.smartview.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author:ccf008
 * @date:2017/10/12 15:23
 * @desc:
 */

public class SmartItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDrawable;
    private int mSpace = 4;
    private int mColor;

    private int itemCount;
    private int rowCount;//列数
    private int headerCount;

    public SmartItemDecoration() {
        this(-1);
    }

    public SmartItemDecoration(int mSpace) {
        this(mSpace, Color.parseColor("#e1e1e1"));
    }

    public SmartItemDecoration(int mSpace, @ColorInt int color) {
        if (mSpace > 0) this.mSpace = mSpace;
        this.mColor = color;
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     */
    public SmartItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDrawable = a.getDrawable(0);
        a.recycle();
    }

    public SmartItemDecoration(Context context, @DrawableRes int drawableId) {
        mDrawable = ContextCompat.getDrawable(context, drawableId);
        mSpace = mDrawable.getIntrinsicHeight();
    }

    /**
     * @param outRect 用于规定分割线的范围
     * @param view    进行分割操作的子View;
     * @param parent  父View
     * @param state   暂时用不到
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        itemCount = parent.getAdapter().getItemCount();
        headerCount = getHeaderCount(parent);

        if (rowCount == 0) {
            RecyclerView.LayoutManager layoutParams = parent.getLayoutManager();
            if (layoutParams instanceof GridLayoutManager) {
                rowCount = ((GridLayoutManager) layoutParams).getSpanCount();
            } else {
                rowCount = 1;
            }
        }

        //获取当前view的位置
        if (!isLastItem(getCurrentItemPosition(view))) {
            outRect.bottom = mSpace;
        } else {
            outRect.bottom = 0;
        }

        //限定在网格布局中
        if (rowCount > 1 && headerCount == 0 && !isRightMost(getCurrentItemPosition(view)))
            outRect.right = mSpace;
        //限定在含有头部的网格布局中
        if (rowCount > 1 && headerCount > 0 && !isHeaderRightMost(getCurrentItemPosition(view)))
            outRect.right = mSpace;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            drawHorizontalDecoration(c, parent.getChildAt(i));

            //限定在网格布局中
            int position = getCurrentItemPosition(parent.getChildAt(i));

            //不带头部的网布局
            if (rowCount > 1 && headerCount == 0 && !isRightMost(position))
                drawVerticalDecoration(c, parent.getChildAt(i));
            //带头部的网格布局
            if (rowCount > 1 && headerCount > 0 && !isHeaderRightMost(position) && !isHeaderView(position))
                drawVerticalDecoration(c, parent.getChildAt(i));
        }
    }

    private void drawVerticalDecoration(Canvas c, View childAt) {
        Rect rect = new Rect();
        rect.top = childAt.getTop();
        rect.bottom = childAt.getBottom();
        rect.left = childAt.getRight();
        rect.right = childAt.getRight() + mSpace;

        //判断绘制的是颜色还好图片
        if (mPaint != null) {
            c.drawRect(rect, mPaint);
        } else {
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
        }
    }

    private void drawHorizontalDecoration(Canvas c, View childAt) {
        if (isLastItem(getCurrentItemPosition(childAt))) return;

        Rect rect = new Rect();
        rect.top = childAt.getBottom();
        rect.bottom = childAt.getBottom() + mSpace;
        rect.left = childAt.getLeft();
        rect.right = childAt.getRight() + mSpace;

        //判断绘制的是颜色还好图片
        if (mPaint != null) {
            c.drawRect(rect, mPaint);
        } else {
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
        }


    }

    /**
     * 判断一个ItemView是否是最后一个
     *
     * @param currentItemPosition
     * @return
     */
    private boolean isLastItem(int currentItemPosition) {
        boolean result = false;
        //判断在无头部的所有布局中是否是最后的item
        if (headerCount == 0 && (currentItemPosition + 1) > (getLines() - 1) * rowCount)
            result = true;

        //判断在有头部且为线性布局中是否为最后的Item
        if (headerCount > 0 && rowCount == 1 && (currentItemPosition + 1) > (getLines() - 1) * rowCount)
            result = true;

        //判断在有头部且为网格布局中是否为最后的Item
        if (headerCount > 0 && rowCount > 1 && (currentItemPosition + headerCount - 1) > (getLines() - 1) * rowCount)
            result = true;
        return result;
    }

    /**
     * 获取子View的当前位置
     *
     * @param view
     * @return
     */
    private int getCurrentItemPosition(View view) {
        return ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
    }


    /**
     * 判断在表格布局中子View是不是在该行的最右边
     *
     * @param currentItemPosition
     * @return
     */
    private boolean isRightMost(int currentItemPosition) {
        return (currentItemPosition + 1) % rowCount == 0;
    }


    /**
     * 获取总行数
     *
     * @return
     */
    private int getLines() {
        int a = itemCount / rowCount;
        return itemCount % rowCount == 0 ? a : a + 1;
    }

    /**
     * 获取头部的数量
     *
     * @param recyclerView
     * @return
     */
    private int getHeaderCount(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof SmartAdapter) {
            return ((SmartAdapter) adapter).getHeaderCount();
        } else {
            return 0;
        }
    }

    /**
     * 判断在表格布局中子View是不是在该行的最右边
     *
     * @param currentItemPosition
     * @return
     */
    private boolean isHeaderView(int currentItemPosition) {
        return currentItemPosition < headerCount;
    }

    /**
     * 判断在存在头部的表格布局中子View是不是在该行的最右边
     *
     * @param currentItemPosition
     * @return
     */
    private boolean isHeaderRightMost(int currentItemPosition) {
        return currentItemPosition % rowCount == 0;
    }
}
