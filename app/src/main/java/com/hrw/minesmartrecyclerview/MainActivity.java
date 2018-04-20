package com.hrw.minesmartrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.hrw.smartrecyclerviewlibrary.OnSmartItemClickListener;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartItemDecoration;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

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
        List<String> strings = new ArrayList<>();
        strings.add("测试数据一");
        strings.add("测试数据二");
        strings.add("测试数据三");
        strings.add("测试数据四");
        SmartAdapter<String> smartAdapter = new SmartAdapter<String>(strings, R.layout.item_list_mainactivity) {
            @Override
            protected void bindView(SmartVH holder, String o, int position) {
                holder.getText(R.id.item_list_name).setText(o);
            }
        };

        View view = LayoutInflater.from(this).inflate(R.layout.header_recycler, null);
        smartAdapter.setHeaderView(view);
        recyclerView.setAdapter(smartAdapter);
        smartAdapter.setOnSmartItemClickListener(new OnSmartItemClickListener<String>() {
            @Override
            public void onSmartItemClick(String s, int position) {

            }
        });
    }
}
