package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/11/19.
 */

public class OrderShopperAdapter extends RecyclerView.Adapter<OrderShopperAdapter.ViewHolder> {

    private Context context;
    private List<ShopperBean<List<ProductBean>>> list;

    public OrderShopperAdapter(Context context, List<ShopperBean<List<ProductBean>>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_shopper, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtShopperName.setText(list.get(position).getSellerName());

        List<ProductBean> product = this.list.get(position).getList();
        OrderProductAdapter adapter = new OrderProductAdapter(context, product);
        holder.rvOrderProduct.setNestedScrollingEnabled(false);
        holder.rvOrderProduct.setLayoutManager(new LinearLayoutManager(context));
        holder.rvOrderProduct.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        holder.rvOrderProduct.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_shopper_name)
        TextView txtShopperName;
        @BindView(R.id.rv_order_product)
        RecyclerView rvOrderProduct;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
