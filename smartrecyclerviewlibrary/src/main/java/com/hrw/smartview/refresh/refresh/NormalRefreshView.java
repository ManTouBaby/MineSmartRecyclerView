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
public class NormalRefreshView extends RelativeLayout implements BaseRefreshView{
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
    public void setRefreshStatus(RefreshStatus refreshStatus) {

    }
}
