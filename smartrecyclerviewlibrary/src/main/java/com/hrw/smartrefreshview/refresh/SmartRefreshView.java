package com.hrw.smartrefreshview.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/07/26 10:55
 * @desc:
 */
public class SmartRefreshView extends ViewGroup {
    private boolean isLoadMore = true;
    private boolean isRefresh = true;
    private boolean isLoadMoreComplete = false;
    private boolean isRefreshComplete = false;

    View view = null;

    public SmartRefreshView(Context context) {
        super(context);
    }

    public SmartRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int childCount = getChildCount();
        if (childCount > 1) new Throwable("the SmartRefreshView can only hold one child view");
        view = getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        NestedScrollView

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
