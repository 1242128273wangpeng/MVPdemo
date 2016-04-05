package com.example.administrator.recycleview.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.recycleview.R;
import com.example.administrator.recycleview.bean.MyData;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/5.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_ITEM = 0;
    private final int TYPE_FOOTER = 1;
    private List<MyData> listDatas;
    private WeakReference<Activity> contextweak;
    private boolean isCompleteFill = false;
    public RecyclerAdapter(Context context, List<MyData> mydatas){
        listDatas = new ArrayList<MyData>();
        listDatas = mydatas;
        contextweak = new WeakReference<Activity>((Activity)context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = contextweak.get();
        View view;
        if(viewType==TYPE_ITEM){
            view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
            return new ItemViewHolder(view);
        }else if(viewType==TYPE_FOOTER&&isCompleteFill){
            view = LayoutInflater.from(context).inflate(R.layout.item_footer,parent,false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RecyclerAdapter.ItemViewHolder itemholder = (RecyclerAdapter.ItemViewHolder)holder;
        MyData mydata = listDatas.get(position);
        itemholder.myDesc.setText(mydata.getDesc());
        itemholder.myMame.setText(mydata.getName());
        itemholder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(contextweak.get(), "删除"+position+listDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
               listDatas.remove(position);
                notifyItemRangeChanged(position-1,listDatas.size());
                return true;
            }
        });
        itemholder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contextweak.get(), "点击"+position+listDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(contextweak.get()).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png").into(itemholder.myIcon);
    }

    @Override
    public int getItemViewType(int position) {
        // 如果没有充满屏幕或者还没有到达最后一个就返回普通的Item
        System.out.println("getItemViewType" + position + "  getItemCount" + getItemCount()+"isCompleteFill"+isCompleteFill);
        if(!isCompleteFill||position+1!=getItemCount()){
            System.out.println("TYPE_ITEM");
            return TYPE_ITEM;
        }else{
            System.out.println("TYPE_FOOTER");
            return TYPE_FOOTER;
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount-->isCompleteFill"+isCompleteFill);
        if(isCompleteFill){ // 如果充满屏幕就将更多的Footer加在最后
            System.out.println("listDatas.size()+1"+listDatas.size()+1);
            return listDatas.size()+1;
        }else{ // 否则就隐藏掉Footer
            System.out.println("listDatas.size()"+listDatas.size());
            return listDatas.size();
        }
    }
    //更改手机屏幕是否被占满的状态
    public void changeIsCompleteFill(boolean status){
        isCompleteFill = status;
        notifyDataSetChanged();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name_tv)
        TextView myMame;
        @Bind(R.id.desc_tv)
       TextView myDesc;
        @Bind(R.id.icon_iv)
         ImageView myIcon;
        @Bind(R.id.itembgview)
        View view;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
