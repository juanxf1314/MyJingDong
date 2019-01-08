package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/10/18.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> {
    private Context context;
    private List<ShopListBean.DataBean> list;

    public ShopListAdapter(Context context, List<ShopListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    private boolean isFlow = false;

    public void setFlow(boolean flow) {
        isFlow = flow;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (isFlow) {
            view = View.inflate(context, R.layout.item_shop_recommend, null);
//            view = LayoutInflater.from(context).inflate(R.layout.item_shop_recommend, parent, false);
        } else {
            view = View.inflate(context, R.layout.item_linear_shop, null);
//            view = LayoutInflater.from(context).inflate(R.layout.item_linear_shop, parent, false);
        }
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ShopListBean.DataBean listBean = list.get(position);
        String images = listBean.getImages();
        if (images.contains("|")) {
            String replace = images.replace("|", ",");
            String[] split = replace.split(",");
            Picasso.with(context).load(Https2http.replace(split[0])).into(holder.imgLogo);
            Log.d("aaa", "onBindViewHolder: " + listBean.getImages());
        } else {
            Picasso.with(context).load(Https2http.replace(images)).into(holder.imgLogo);
        }

        holder.txtTitle.setText(listBean.getTitle());
        holder.txtPrice.setText("￥" + listBean.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo)
        ImageView imgLogo;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_price)
        TextView txtPrice;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
