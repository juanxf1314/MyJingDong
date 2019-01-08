package com.bwie.juan_mao.jingdong_kanglijuan.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.widget.AddDecreaseWidget;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/10/26.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductBean> list;

    public ProductAdapter(Context context, List<ProductBean> list) {
        this.context = context;
        this.list = list;
    }

    // 二级列表商品变化监听
    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener onProductClickListener;

    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    // 加减器监听
    public interface OnAddDecreaseProductListener {
        void onChange(int position, int num);
    }

    private OnAddDecreaseProductListener onAddDecreaseProductListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener onAddDecreaseProductListener) {
        this.onAddDecreaseProductListener = onAddDecreaseProductListener;
    }

    /*// 对于商品选中状态的监听回调
    public interface OnProductSelectedChangeListener {
        void onChange(int pid, int num, int selected);
    }

    private OnProductSelectedChangeListener onProductSelectedChangeListener;

    public void setOnProductSelectedChangeListener(OnProductSelectedChangeListener onProductSelectedChangeListener) {
        this.onProductSelectedChangeListener = onProductSelectedChangeListener;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_cart_product, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ProductBean product = list.get(position);
        String[] images = product.getImages().replace("|", ",").split(",");
        Picasso.with(context).load(Https2http.replace(images[0])).into(holder.imgLogoPro);
        holder.txtTitle.setText(product.getTitle());
        holder.txtBargainPrice.setText("优惠价：￥" + product.getPrice());

        holder.advWidget.setNum(product.getNum());

        holder.advWidget.setOnAddDecreaseClickListener(new AddDecreaseWidget.OnAddDecreaseClickListener() {
            @Override
            public void onAddClick(int num) {
                product.setNum(num);

                /*if (onProductSelectedChangeListener != null) {
                    onProductSelectedChangeListener.onChange(product.getPid(), product.getNum(), product.isChecked() == true ? 1 : 0);
                }*/

                if (onAddDecreaseProductListener != null) {
                    onAddDecreaseProductListener.onChange(position, num);
                }
            }

            @Override
            public void onDecreaseClick(int num) {
                product.setNum(num);

                /*if (onProductSelectedChangeListener != null) {
                    onProductSelectedChangeListener.onChange(product.getPid(), product.getNum(), product.isChecked() == true ? 1 : 0);
                }*/

                if (onAddDecreaseProductListener != null) {
                    onAddDecreaseProductListener.onChange(position, num);
                }
            }
        });
        /*// 判断商品的选中状态
        int selected = product.getSelected();
        if (selected == 0) {
            holder.cbProduct.setChecked(false);
        } else {
            holder.cbProduct.setChecked(true);
        }
        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 接口回传更改接口数据
                product.setChecked(b);

                if (onProductClickListener != null) {
                    onProductClickListener.onProductClick(position, b);
                }
            }
        });*/
        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(product.isChecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                product.setChecked(b);
                /*if (onProductSelectedChangeListener != null) {
                    onProductSelectedChangeListener.onChange(product.getPid(), product.getNum(), b == true ? 1 : 0);
                }*/

                if (onProductClickListener != null) {
                    onProductClickListener.onProductClick(position, b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_product)
        CheckBox cbProduct;
        @BindView(R.id.img_logo_pro)
        ImageView imgLogoPro;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_bargain_price)
        TextView txtBargainPrice;
        @BindView(R.id.adv_widget)
        AddDecreaseWidget advWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
