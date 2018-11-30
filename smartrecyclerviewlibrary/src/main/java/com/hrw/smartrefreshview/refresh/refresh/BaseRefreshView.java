package com.hrw.smartrefreshview.refresh.refresh;

import com.hrw.smartrefreshview.refresh.BaseView;
import com.hrw.smartrefreshview.refresh.RefreshStatus;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/07/26 10:47
 * @desc:
 */
public interface BaseRefreshView extends BaseView {
    void setRefreshStatus(RefreshStatus refreshStatus);
}
