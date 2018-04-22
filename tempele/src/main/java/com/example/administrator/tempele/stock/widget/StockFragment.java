package com.example.administrator.tempele.stock.widget;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.bean.RightModel;
import com.example.administrator.tempele.bean.StockListBean;
import com.example.administrator.tempele.bean.StockListRootBean;
import com.example.administrator.tempele.stock.MyLeftAdapter;
import com.example.administrator.tempele.stock.MyRightAdapter;
import com.example.administrator.tempele.stock.presenter.StockPresenter;
import com.example.administrator.tempele.stock.presenter.StockPresenterImpl;
import com.example.administrator.tempele.stock.view.StockView;
import com.example.administrator.tempele.utils.UtilTools;
import com.example.administrator.tempele.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class StockFragment extends Fragment implements StockView {
    private LinearLayout leftContainerView;
    private ListView leftListView;
    private List<String> leftlList;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private List<RightModel> models;
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    private ProgressDialog progressDialog;
    private MyLeftAdapter myLeftadapter;
    private MyRightAdapter myRightAdapter;
    private StockPresenter stockPrsenter;
    public StockFragment() {
    }

    public static StockFragment newInstance() {
        StockFragment fragment = new StockFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        stockPrsenter = new StockPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View stockview = inflater.inflate(R.layout.fragment_stock, container, false);
        leftContainerView = (LinearLayout) stockview.findViewById(R.id.left_container);
        leftListView = (ListView) stockview.findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) stockview.findViewById(R.id.right_container);
        rightListView = (ListView) stockview.findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) stockview.findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) stockview.findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);
        // 添加左边内容数据
        leftContainerView.setBackgroundColor(Color.YELLOW);
        myLeftadapter = new MyLeftAdapter(getActivity(), new ArrayList<String>());
        leftListView.setAdapter(myLeftadapter);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);
        // 添加右边内容数据
        rightContainerView.setBackgroundColor(Color.GRAY);
        myRightAdapter = new MyRightAdapter(getActivity(), new ArrayList<RightModel>());
        rightListView.setAdapter(myRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);
        onRefresh();
        return stockview;
    }

    private void onRefresh() {
        stockPrsenter.loadStock(1);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("正在加载...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void addStocks(List<StockListBean> stockListBeent) {
//        myLeftadapter.notifyDataSetChanged();
    }

    @Override
    public void showFail() {
        Toast.makeText(getActivity(), "加载失败...", Toast.LENGTH_SHORT).show();
    }
}
