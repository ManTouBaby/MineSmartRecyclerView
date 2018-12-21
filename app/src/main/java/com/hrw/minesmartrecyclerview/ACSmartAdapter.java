package com.hrw.minesmartrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.hrw.smartview.adapter.SmartAdapter;
import com.hrw.smartview.adapter.SmartItemDecoration;
import com.hrw.smartview.adapter.SmartVH;
import com.hrw.smartview.listener.OnSmartItemChildClickListener;
import com.hrw.smartview.listener.OnSmartItemClickListener;
import com.hrw.smartview.listener.OnSmartItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/30 14:22
 * @desc:
 */
public class ACSmartAdapter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_smart_adater_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SmartItemDecoration());
        List<TestBO> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestBO testBO = new TestBO();
            testBO.setName("测试数据" + (i + 1));
            if (i == 3 || i == 7) {
                testBO.setItemType(1);
            } else {
                testBO.setItemType(0);
            }
            strings.add(testBO);
        }
        SmartAdapter<TestBO> smartAdapter = new SmartAdapter<TestBO>(R.layout.item_list_mainactivity) {
            @Override
            protected void bindView(SmartVH holder, TestBO o, int position) {
                holder.getText(R.id.item_list_name).setText(o.getName());
                addChildClickListener(holder.getViewById(R.id.iv_img_01), o, position);
                addChildClickListener(holder.getViewById(R.id.iv_img_02), o, position);
                addChildClickListener(holder.getViewById(R.id.iv_img_03), o, position);
            }
        };
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        smartAdapter.setItemType(0, R.layout.item_list_mainactivity);
        smartAdapter.setItemType(1, R.layout.item_list_mainactivity_1pic);
        smartAdapter.setHeaderView(view, view1, view2);
        smartAdapter.setFooterView(view3, view4, view5);
        recyclerView.setAdapter(smartAdapter);
        smartAdapter.setDate(strings);
        smartAdapter.setOnSmartItemClickListener(new OnSmartItemClickListener<TestBO>() {
            @Override
            public void onSmartItemClick(TestBO testBO, int position) {
                System.out.println("result-----" + testBO.getName());
            }
        });
        smartAdapter.setOnSmartItemLongClickListener(new OnSmartItemLongClickListener<TestBO>() {
            @Override
            public void onSmartItemLongClick(TestBO testBO, int position) {
                System.out.println("测试长按:" + testBO.getName());
            }
        });
        smartAdapter.setOnSmartItemChildClickListener(new OnSmartItemChildClickListener<TestBO>() {
            @Override
            public void onSmartItemClick(View view, TestBO testBO, int position) {
                switch (view.getId()) {
                    case R.id.iv_img_01:
                        Toast.makeText(ACSmartAdapter.this, "单击图片一", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_img_02:
                        Toast.makeText(ACSmartAdapter.this, "单击图片二", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_img_03:
                        Toast.makeText(ACSmartAdapter.this, "单击图片三", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
