package com.hrw.smartrefreshview.refresh;

import com.hrw.smartrefreshview.refresh.loadmore.BaseLoadMoreView;
import com.hrw.smartrefreshview.refresh.refresh.BaseRefreshView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/29 18:25
 * @desc:
 */
public interface BaseSmartRefresh {
    void setRefreshView(BaseRefreshView onRefreshView);

    void setLoadMoreView(BaseLoadMoreView onLoadMoreView);

    void setRefreshEnable(boolean enable);

    void setLoadMoreEnable(boolean enable);


}
