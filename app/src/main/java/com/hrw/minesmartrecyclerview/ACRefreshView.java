package com.hrw.minesmartrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hrw.smartview.SmartRefreshView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/30 14:22
 * @desc:
 */
public class ACRefreshView extends AppCompatActivity {
    SmartRefreshView refreshView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_smart_refresh_layout);
//        refreshView = (SmartRefreshView) findViewById(R.id.srv_show);
    }

    public void onRedrawClick(View view) {
        System.out.println("重绘");
//        refreshView.invalidate();
    }
}
