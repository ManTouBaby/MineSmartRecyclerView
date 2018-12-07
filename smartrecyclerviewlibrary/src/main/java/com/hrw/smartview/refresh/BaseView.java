package com.hrw.smartview.refresh;

import android.view.View;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/30 16:54
 * @desc:
 */
public interface BaseView {
    /**
     * 控件本身
     *
     * @return
     */
    View getView();

    /**
     * 加载过程中显示在界面上的高度
     *
     * @return
     */
    int getVisitHeight();

    void startAnimate();

    void stopAnimate();
}
