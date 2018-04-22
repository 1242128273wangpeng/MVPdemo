package com.example.administrator.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.recyclerviewdemo.adapter.RecyclerAdapter;
import com.example.administrator.recyclerviewdemo.bean.MyData;
import com.example.administrator.recycleview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_RefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager layoutManage;
    private List<MyData> lists;
    private RecyclerAdapter adapter;
    private int lastItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        lists = new ArrayList<MyData>();
        for (int i = 0; i < 30; i++) {
            MyData mdata = new MyData("Mydata"+i+"的描述","","Mydata"+i+"Name");
            lists.add(mdata);
        }
        layoutManage = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManage);
        System.out.println(lists.size());
        adapter = new RecyclerAdapter(this,lists);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItem = layoutManage.findLastVisibleItemPosition();
//                if (dy > 0) {
//                    Toast.makeText(MainActivity.this,"up",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivit y.this,"down",Toast.LENGTH_SHORT).show();
//                }
                 System.out.println("lastItem-->"+lastItem);
//                System.out.println("lastCompleteItem-->"+layoutManage.findLastCompletelyVisibleItemPosition());
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                System.out.println("----------------"+adapter.getItemCount());

                    if(isCanLoadMore()){
                        adapter.changeIsCompleteFill(true);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE&&lastItem+1==adapter.getItemCount()) {
                            System.out.println("加载更多");
                        }
//                    swipeRefreshLayout.setRefreshing(true);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            int before =  lists.size();
//                            for (int i = 0; i < 5; i++) {
//                               MyData myadddata = new MyData("增加的Mydata"+i+"的描述","","增加的Mydata"+i+"Name");
//                                lists.add(myadddata);
//                            }
//                            int after = lists.size();
//                            System.out.println("增加了");
//                            adapter.notifyItemRangeChanged(before,after);
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
//                    },3000);
                }
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
    private boolean isCanLoadMore(){
        // 获取到可以看见的子item的个数
          int visiableitemcount = recyclerView.getChildCount();
        // 如果可视的View是所有的子view
        if(visiableitemcount==adapter.getItemCount()){
           return false;
        }
        return true;
    }

}
