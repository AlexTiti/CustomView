package com.example.administrator.customview.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.administrator.customview.R;
import com.example.administrator.customview.recyclerview.decoration.DevicerDecoration;
import com.example.administrator.customview.recyclerview.decoration.StickDecoration;
import com.example.administrator.customview.recyclerview.enclosure.MyItemTouchHelper;
import com.example.administrator.customview.recyclerview.enclosure.RecycleViewClickListenering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity implements StickDecoration.GroupSelect {

    List<String> strings;
    Map<String, String> map;
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        init();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置padding
        //mRecyclerView.addItemDecoration(new CustomDecoration(30));
        //设置下划线
        mRecyclerView.addItemDecoration(new DevicerDecoration(this, 1, R.color.colorAccent));
        // mRecyclerView.addItemDecoration(new StickDecoration(this,this));
        RecycleViewAdapter adapter = new RecycleViewAdapter(strings);

        itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(adapter);


        mRecyclerView.addOnItemTouchListener(new RecycleViewClickListenering(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
            }
            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                if (position == 0) {
                    return;
                }
                itemTouchHelper.startDrag(viewHolder);

            }
        });

    }

    private void init() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("A=" + i);
        }
        for (int i = 0; i < 10; i++) {
            list.add("BB=" + i);
        }
        for (int i = 0; i < 10; i++) {
            list.add("CCCC=" + i);
        }
        for (int i = 0; i < 10; i++) {
            list.add("DDDD=" + i);
        }
        strings = list;


        map = new HashMap<>();
        map.put("A", "This is A");
        map.put("B", "This is B");
        map.put("C", "This is C");
        map.put("D", "This is D");
    }


    @Override
    public boolean isNextGroup(int position) {
        if (position == 0) {
            return true;
        }
        if (strings.get(position - 1).charAt(0) == strings.get(position).charAt(0)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLastGroup(int position) {
        if (strings.get(position + 1).charAt(0) == strings.get(position).charAt(0)) {
            return false;
        }
        return true;
    }

    @Override
    public String getGroupTitle(int position) {

        return map.get(String.valueOf(strings.get(position).charAt(0)));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_animal,R.anim.exit_animal);
    }
}
