package com.hrw.smartview.listener;

import android.view.View;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/04 10:47
 * @desc:
 */
public interface OnSmartItemChildClickListener<V> {
    void onSmartItemClick(View view, V v, int position);
}
