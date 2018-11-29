package com.hrw.minesmartrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.hrw.smartrefreshview.listener.OnSmartItemClickListener;
import com.hrw.smartrefreshview.adapter.SmartAdapter;
import com.hrw.smartrefreshview.adapter.SmartItemDecoration;
import com.hrw.smartrefreshview.adapter.SmartVH;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_show);
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
    }
}
