package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/11/13.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_01 = 0;
    private static final int TYPE_02 = 1;
    private static final int TYPE_03 = 2;

    private Context context;
    private List<NewsBean.DataBean> list;

    public NewsAdapter(Context context, List<NewsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        NewsBean.DataBean dataBean = list.get(position);
        if (!TextUtils.isEmpty(dataBean.getThumbnail03())) {
            return TYPE_03;
        } else if (!TextUtils.isEmpty(dataBean.getThumbnail02())) {
            return TYPE_02;
        } else {
            return TYPE_01;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_01:
                View view1 = LayoutInflater.from(context).inflate(R.layout.item_news_01, parent, false);
                holder = new ViewHolder01(view1);
                break;
            case TYPE_02:
                View view2 = LayoutInflater.from(context).inflate(R.layout.item_news_02, parent, false);
                holder = new ViewHolder02(view2);
                break;
            case TYPE_03:
                View view3 = LayoutInflater.from(context).inflate(R.layout.item_news_03, parent, false);
                holder = new ViewHolder03(view3);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_01:
                ViewHolder01 holder1 = (ViewHolder01) holder;
                Picasso.with(context).load(list.get(position).getThumbnail01()).into(holder1.imgLogo01);
                holder1.txtTitle.setText(list.get(position).getTitle());
                holder1.txtSub.setText(list.get(position).getAuthorName() + "\t\t" + list.get(position).getDate());
                break;
            case TYPE_02:
                ViewHolder02 holder2 = (ViewHolder02) holder;
                Picasso.with(context).load(list.get(position).getThumbnail01()).into(holder2.imgLogo01);
                Picasso.with(context).load(list.get(position).getThumbnail02()).into(holder2.imgLogo02);
                holder2.txtTitle.setText(list.get(position).getTitle());
                holder2.txtSub.setText(list.get(position).getAuthorName() + "\t\t" + list.get(position).getDate());
                break;
            case TYPE_03:
                ViewHolder03 holder3 = (ViewHolder03) holder;
                Picasso.with(context).load(list.get(position).getThumbnail01()).into(holder3.imgLogo01);
                Picasso.with(context).load(list.get(position).getThumbnail02()).into(holder3.imgLogo02);
                Picasso.with(context).load(list.get(position).getThumbnail03()).into(holder3.imgLogo03);
                holder3.txtTitle.setText(list.get(position).getTitle());
                holder3.txtSub.setText(list.get(position).getAuthorName() + "\t\t" + list.get(position).getDate());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder01 extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_sub)
        TextView txtSub;
        @BindView(R.id.img_logo01)
        ImageView imgLogo01;

        public ViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder02 extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_sub)
        TextView txtSub;
        @BindView(R.id.img_logo01)
        ImageView imgLogo01;
        @BindView(R.id.img_logo02)
        ImageView imgLogo02;

        public ViewHolder02(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder03 extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_sub)
        TextView txtSub;
        @BindView(R.id.img_logo01)
        ImageView imgLogo01;
        @BindView(R.id.img_logo02)
        ImageView imgLogo02;
        @BindView(R.id.img_logo03)
        ImageView imgLogo03;

        public ViewHolder03(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
