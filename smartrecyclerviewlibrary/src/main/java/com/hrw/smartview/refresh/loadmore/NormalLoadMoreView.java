package com.hrw.smartview.refresh.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.hrw.smartview.refresh.LoadMoreStatus;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/29 18:48
 * @desc:
 */
public class NormalLoadMoreView extends RelativeLayout implements BaseLoadMoreView {
    public NormalLoadMoreView(Context context) {
        super(context);
    }

    public NormalLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setLoadMoreStatus(LoadMoreStatus moreStatus) {

    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public int getVisitHeight() {
        return 0;
    }

    @Override
    public void startAnimate() {

    }

    @Override
    public void stopAnimate() {

    }
}
