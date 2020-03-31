package com.hrw.minesmartrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;

import com.hrw.smartview.adapter.SmartAdapter;
import com.hrw.smartview.adapter.SmartItemDecoration;
import com.hrw.smartview.adapter.SmartVH;
import com.hrw.smartview.listener.OnSmartItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    SmartAdapter<TestBO> smartAdapter;

    enum ActivityEnum {
        AC_REFRESH_VIEW(ACRefreshView.class, "刷新控件"),
        AC_SMART_ADAPTER(ACSmartAdapter.class, "多动能Adapter");

        ActivityEnum(Class<?> aClass, String info) {
            mClass = aClass;
            mInfo = info;
        }

        private Class<?> mClass;
        private String mInfo;

        public Class<?> getEnumClass() {
            return mClass;
        }


        public String getInfo() {
            return mInfo;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.rl_goto_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        SparseIntArray integerMap = new SparseIntArray();
        integerMap.put(0, R.layout.item_list_mainactivity);
        integerMap.put(1, R.layout.item_list_mainactivity_1pic);
        View view = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.item_footer, null);

        recyclerView.setAdapter(smartAdapter = new SmartAdapter<TestBO>(integerMap) {
            @Override
            protected void bindView(SmartVH holder, TestBO o, int position) {
                holder.getText(R.id.item_list_name).setText(o.getName());
            }
        });

        smartAdapter.setHeaderView(view, view1, view2);
        smartAdapter.setFooterView(view3, view4, view5);

        smartAdapter.setDate(strings);
        smartAdapter.setOnSmartItemClickListener(new OnSmartItemClickListener<TestBO>() {
            @Override
            public void onSmartItemClick(TestBO testBO, int position) {
                System.out.println("result-----" + testBO.getName() + "--" + position);
            }
        });

    }
}
