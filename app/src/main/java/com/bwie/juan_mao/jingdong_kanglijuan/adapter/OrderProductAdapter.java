package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/11/19.
 */

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.ViewHolder> {
    private Context context;
    private List<ProductBean> list;

    public OrderProductAdapter(Context context, List<ProductBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductBean product = list.get(position);
        String[] images = product.getImages().replace("|", ",").replace("https", "http").split(",");
        Picasso.with(context).load(images[0]).into(holder.imgProductLogo);
        holder.txtProductTitle.setText(product.getTitle());
        holder.txtPrice.setText("￥" + product.getPrice());
        holder.txtNum.setText("x" + product.getNum());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_product_logo)
        ImageView imgProductLogo;
        @BindView(R.id.txt_product_title)
        TextView txtProductTitle;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.txt_num)
        TextView txtNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
