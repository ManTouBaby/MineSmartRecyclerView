package com.hrw.smartrefreshview.refresh;

import com.hrw.smartrefreshview.refresh.loadcontent.BaseLoadContentView;
import com.hrw.smartrefreshview.refresh.loadmore.BaseLoadMoreView;
import com.hrw.smartrefreshview.refresh.refresh.BaseRefreshView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/29 18:25
 * @desc:
 */
public interface BaseSmartRefresh {
    /**
     * 设置下拉刷新动画
     *
     * @param refreshView
     */
    void setRefreshView(BaseRefreshView refreshView);

    /**
     * 设置加载更多动画
     *
     * @param loadMoreView
     */
    void setLoadMoreView(BaseLoadMoreView loadMoreView);

    /**
     * 设置内容加载动画
     *
     * @param loadContentView
     */
    void setLoadView(BaseLoadContentView loadContentView);

    /**
     * 设置下拉刷新可用
     *
     * @param enable
     */
    void setRefreshEnable(boolean enable);

    /**
     * 设置加载更多可用
     *
     * @param enable
     */
    void setLoadMoreEnable(boolean enable);


    /**
     * 开始内容加载动画
     */
    void startLoad();

    /**
     * 结束内容加载动画
     */
    void stopLoad();


}
