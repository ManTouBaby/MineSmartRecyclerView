package com.hrw.smartrefreshview.refresh.loadmore;

import com.hrw.smartrefreshview.refresh.BaseView;
import com.hrw.smartrefreshview.refresh.LoadMoreStatus;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/04/20 上午 9:35
 * @version:1.0.0
 */
public interface BaseLoadMoreView extends BaseView {
    void setLoadMoreStatus(LoadMoreStatus moreStatus);
}
