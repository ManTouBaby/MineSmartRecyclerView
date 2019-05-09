package com.hrw.minesmartrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrw.smartview.adapter.SmartAdapter;
import com.hrw.smartview.adapter.SmartItemDecoration;
import com.hrw.smartview.adapter.SmartVH;
import com.hrw.smartview.listener.OnSmartItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.remove(1);
        integers.remove(1);
        integers.remove(1);
        integers.remove(1);
        integers.remove(1);
        integers.remove(1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_goto_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SmartItemDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(
                new SmartAdapter<ActivityEnum>(ActivityEnum.values(), R.layout.item_string_list_item) {

                    @Override
                    protected void bindView(SmartVH holder, ActivityEnum activityEnum, int position) {
                        holder.getText(R.id.tv_string_label).setText(activityEnum.getInfo());
                    }
                }.setOnSmartItemClickListener(new OnSmartItemClickListener<ActivityEnum>() {
                    @Override
                    public void onSmartItemClick(ActivityEnum activityEnum, int position) {
                        Intent intent = new Intent(MainActivity.this, activityEnum.getEnumClass());
                        startActivity(intent);
                    }
                }));
    }
}
