package com.hrw.smartview.refresh;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/30 16:58
 * @desc:
 */
public enum RefreshStatus {
    PULL_DOWN,  //下拉刷新
    LET_SLIP,   //松开回弹
    REFRESH,    //正在刷新
    COMPLETE    //刷新完成
}
