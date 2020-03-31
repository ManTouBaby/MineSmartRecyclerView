package com.hrw.smartview.refresh.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.hrw.smartview.refresh.RefreshStatus;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/29 18:49
 * @desc:
 */
public class NormalRefreshView extends RelativeLayout implements BaseRefreshView {
    public NormalRefreshView(Context context) {
        super(context);
    }

    public NormalRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
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


    @Override
    public void setRefreshStatus(RefreshStatus refreshStatus, float scrollY) {
        switch (refreshStatus) {
            case REFRESHING://正在刷新
                break;
            case COMPLETE://刷新完成
                break;
            case LET_SLIP://松开立即刷新
                break;
            case PULL_DOWN://下拉可以刷新
                
                break;
        }
    }
}
