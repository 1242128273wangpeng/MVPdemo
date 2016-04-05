package com.example.administrator.tempele.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tempele.R;
import com.example.administrator.tempele.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/27.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<NewsBean> mData;
    private boolean mShowFooter = true;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    public NewsAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        System.out.println("position:"+position+" getItemCount():"+getItemCount());
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewsBean news = mData.get(position);
            if (news == null) {
                return;
            }
            System.out.println(news.toString());
                    ((ItemViewHolder) holder).mTitle.setText(news.getTitle());
            ((ItemViewHolder)holder).mDes.setText(news.getContent());
            if("".equals(news.getImg())){
                Picasso.with(mContext).load(R.drawable.dot).into(((ItemViewHolder) holder).mNewsImg);
            }else {
                System.out.println("news.getImg():"+news.getImg());
                Picasso.with(mContext).load(news.getImg()).into(((ItemViewHolder) holder).mNewsImg);
            }
        }
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }

   public void setDatas(List<NewsBean> datas) {
        this.mData = datas;
        this.notifyDataSetChanged();
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tvTitle)
        TextView mTitle;
        @Bind(R.id.tvDesc)
        TextView mDes;
        @Bind(R.id.ivNews)
        ImageView mNewsImg;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mNewsImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }
}
