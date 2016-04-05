package com.example.administrator.recycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.recycleview.adapter.RecyclerAdapter;
import com.example.administrator.recycleview.bean.MyData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_RefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager layoutManage;
    private List<MyData> lists;
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
        final RecyclerAdapter adapter = new RecyclerAdapter(this,lists);
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
                System.out.println("adapter.getItemCount()"+adapter.getItemCount());
                if (newState == RecyclerView.SCROLL_STATE_IDLE&&lastItem+1==adapter.getItemCount()) {
                    System.out.println("加载更多");
                    swipeRefreshLayout.setRefreshing(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int before =  lists.size();
                            for (int i = 0; i < 5; i++) {
                               MyData myadddata = new MyData("增加的Mydata"+i+"的描述","","增加的Mydata"+i+"Name");
                                lists.add(myadddata);
                            }
                            int after = lists.size();
                            System.out.println("增加了");
                            adapter.notifyItemRangeChanged(before,after);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    },3000);
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
}
