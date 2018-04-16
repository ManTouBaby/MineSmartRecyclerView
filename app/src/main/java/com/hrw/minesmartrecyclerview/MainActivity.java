package com.hrw.minesmartrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
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
        List<String> strings = new ArrayList<>();
        strings.add("测试数据一");
        strings.add("测试数据二");
        strings.add("测试数据三");
        strings.add("测试数据四");
        recyclerView.setAdapter(new SmartAdapter<String>(strings, android.R.layout.test_list_item) {
            @Override
            protected void bindView(SmartVH holder, String o, int position) {
                holder.getText(android.R.id.text1).setText(o);
            }
        });
    }
}
