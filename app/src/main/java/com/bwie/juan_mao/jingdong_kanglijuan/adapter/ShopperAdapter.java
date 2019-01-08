package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 卷猫~ on 2018/10/26.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder> {

    private Context context;
    private List<ShopperBean<List<ProductBean>>> list;

    public ShopperAdapter(Context context, List<ShopperBean<List<ProductBean>>> list) {
        this.context = context;
        this.list = list;
    }

    // 一级列表（商家）发生变化的接口
    public interface OnShopperClickListener {
        void onShopperClick(int position, boolean isCheck);
    }

    private OnShopperClickListener shopperClickListener;

    public void setOnShopperClickListener(OnShopperClickListener listener) {
        this.shopperClickListener = listener;
    }


    // 二级列表的加减器监听
    private ProductAdapter.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ProductAdapter.OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    /*// 对于商品选中状态的监听回调
    public interface OnProductSelectedChangeListener {
        void onChange(int sellerid, int pid, int num, int selected);
    }

    private OnProductSelectedChangeListener onProductSelectedChangeListener;

    public void setOnProductSelectedChangeListener(OnProductSelectedChangeListener onProductSelectedChangeListener) {
        this.onProductSelectedChangeListener = onProductSelectedChangeListener;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shopper, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ShopperBean<List<ProductBean>> shopper = list.get(position);
        holder.txtShopperName.setText(shopper.getSellerName());

        // 产品的列表
        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(context);
        holder.rvCartProduct.setLayoutManager(pLayoutManager);
        holder.rvCartProduct.setNestedScrollingEnabled(false);
        final ProductAdapter adapter = new ProductAdapter(context, shopper.getList());
        // 给二级列表添加一个加减器的监听
        if (productListener != null) {
            adapter.setOnAddDecreaseProductListener(productListener);
        }
        /*// 改变商品信息的监听
        adapter.setOnProductSelectedChangeListener(new ProductAdapter.OnProductSelectedChangeListener() {
            @Override
            public void onChange(int pid, int num, int selected) {
                if (onProductSelectedChangeListener != null) {
                    onProductSelectedChangeListener.onChange(Integer.parseInt(shopper.getSellerid()), pid, num, selected);
                }
            }
        });*/
        // 二级条目（商品）复选框点击事件
        adapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                // 当前商品未选中，商家也就未选中
                if (!isChecked) {
                    shopper.setChecked(false);
                    // 只要是当前条目未选中，全选复选框也就没选中
                    shopperClickListener.onShopperClick(position, false);
                } else {
                    // 当前商品如果选中，需要遍历商家所有的商品是否选中
                    // 循环遍历之前先设置一个true标志位，只要有一条商品没有被选中，商家也就选中，标志位变成false
                    boolean isAllProductSelected = true;
                    for (ProductBean product : shopper.getList()) {
                        if (!product.isChecked()) {
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    shopper.setChecked(isAllProductSelected);
                    // 当前商品选中时，需要循环遍历所有的商家是否被选中来确认外部全选复选框的状态
                    shopperClickListener.onShopperClick(position, true);
                }
                // 数据发生变化之后刷新适配器
                notifyDataSetChanged();
                productListener.onChange(0, 0);
            }
        });

        holder.rvCartProduct.setAdapter(adapter);

        // 先取消掉之前的点击变化监听
        holder.cbShopper.setOnCheckedChangeListener(null);

        // 设置好初始化的状态
        holder.cbShopper.setChecked(shopper.isChecked());

        // 等设置完初始化状态之后再设置我们自己的监听
        // 商家列表中的复选框
        holder.cbShopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopper.setChecked(isChecked);

                // 1.商家被选中的时候，子类所有的商品应该被选中
//                if (isChecked) {
                List<ProductBean> productList = shopper.getList();
                for (ProductBean product : productList) {
                    product.setChecked(isChecked);
                }
                // 子类商品的适配器刷新
                adapter.notifyDataSetChanged();
//                }
                // 当点击一级条目的时候，外部的全选按钮状态发生变化
                if (shopperClickListener != null) {
                    shopperClickListener.onShopperClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_shopper)
        CheckBox cbShopper;
        @BindView(R.id.txt_shopper_name)
        TextView txtShopperName;
        @BindView(R.id.rv_cart_product)
        RecyclerView rvCartProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
