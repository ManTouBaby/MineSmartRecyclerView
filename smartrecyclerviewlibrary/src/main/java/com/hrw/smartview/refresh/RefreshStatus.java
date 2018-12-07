package com.hrw.smartview.refresh;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/30 16:58
 * @desc:
 */
public enum RefreshStatus {
    PULL_DOWN("下拉可以刷新"),  //下拉刷新
    LET_SLIP("松快立即刷新"),   //松开回弹
    REFRESHING("正在刷新"),    //正在刷新
    COMPLETE("刷新完成");   //刷新完成


    String mValue;

    RefreshStatus(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }
}
