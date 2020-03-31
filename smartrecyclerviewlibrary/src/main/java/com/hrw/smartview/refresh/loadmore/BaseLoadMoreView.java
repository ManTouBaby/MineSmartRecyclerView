package com.hrw.smartview.refresh.loadmore;

import com.hrw.smartview.refresh.BaseView;
import com.hrw.smartview.refresh.LoadMoreStatus;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/04/20 上午 9:35
 * @version:1.0.0
 */
public interface BaseLoadMoreView extends BaseView {
    void setLoadMoreStatus(LoadMoreStatus moreStatus);
}
