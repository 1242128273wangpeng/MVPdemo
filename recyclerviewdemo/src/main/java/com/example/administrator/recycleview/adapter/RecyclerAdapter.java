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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private List<MyData> listDatas;
    private WeakReference<Activity> contextweak;
    public RecyclerAdapter(Context context, List<MyData> mydatas){
        listDatas = new ArrayList<MyData>();
        listDatas = mydatas;
        contextweak = new WeakReference<Activity>((Activity)context);
    }
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = contextweak.get();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
       MyData mydata = listDatas.get(position);
        holder.myDesc.setText(mydata.getDesc());
        holder.myMame.setText(mydata.getName());
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(contextweak.get(), "删除"+position+listDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
               listDatas.remove(position);
                notifyItemRangeChanged(position-1,listDatas.size());
                return true;
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contextweak.get(), "点击"+position+listDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(contextweak.get()).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png").into(holder.myIcon);
    }

    @Override
    public int getItemCount() {
      return listDatas.size();
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
}
