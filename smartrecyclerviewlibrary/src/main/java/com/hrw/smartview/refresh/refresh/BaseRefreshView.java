package com.hrw.smartview.refresh.refresh;

import com.hrw.smartview.refresh.BaseView;
import com.hrw.smartview.refresh.RefreshStatus;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/07/26 10:47
 * @desc:
 */
public interface BaseRefreshView extends BaseView {
    void setRefreshStatus(RefreshStatus refreshStatus);
}
