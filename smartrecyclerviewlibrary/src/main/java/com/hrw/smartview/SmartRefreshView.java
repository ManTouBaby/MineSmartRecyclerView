package com.hrw.smartview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hrw.smartview.refresh.loadcontent.BaseLoadContentView;
import com.hrw.smartview.refresh.loadmore.BaseLoadMoreView;
import com.hrw.smartview.refresh.loadmore.NormalLoadMoreView;
import com.hrw.smartview.refresh.refresh.BaseRefreshView;
import com.hrw.smartview.refresh.refresh.NormalRefreshView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/07/26 10:55
 * @desc:
 */
public class SmartRefreshView extends ViewGroup {
    private Context mContext;

    private boolean loadMoreEnable = true;
    private boolean refreshEnable = true;

    private BaseRefreshView refreshView;
    private BaseLoadMoreView loadMoreView;
    private BaseLoadContentView loadContentView;
    private View contentView;

    public SmartRefreshView(Context context) {
        super(context);
    }

    public SmartRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        refreshView = new NormalRefreshView(mContext);
        loadMoreView = new NormalLoadMoreView(mContext);
        addView(refreshView.getView(), 0);
        addView(loadMoreView.getView(), 2);

        int childCount = getChildCount();
        if (childCount > 3) new Throwable("the SmartRefreshView can only hold one child view");
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (!(view instanceof BaseRefreshView) && !(view instanceof BaseLoadMoreView)) {
                contentView = view;
            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("布局");
    }


}
