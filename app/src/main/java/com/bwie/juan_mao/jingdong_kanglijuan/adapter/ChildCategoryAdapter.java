package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/10/19.
 */

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.MyViewHolder> {
    private Context context;
    private List<ProductCategoryBean.DataBean> list;

    public ChildCategoryAdapter(Context context, List<ProductCategoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnChildClickListener {
        void onChildClick(View view, int position, ProductCategoryBean.DataBean bean);
    }

    private OnChildClickListener childClickListener;

    public void setOnChildClickListener(OnChildClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child_category_right, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ProductCategoryBean.DataBean dataBean = list.get(position);
        holder.txtChildCategory.setText(dataBean.getName());

        // 设置适配器
        List<ProductCategoryBean.DataBean.ListBean> listBeans = dataBean.getList();
        CategoryAdapterTwo categoryAdapterTwo = new CategoryAdapterTwo(context, listBeans);
        holder.rvChildName.setLayoutManager(new GridLayoutManager(context, 4));
        categoryAdapterTwo.setOnItemClickListener(new CategoryAdapterTwo.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positions) {
                if (childClickListener != null) {
                    childClickListener.onChildClick(view, positions, dataBean);
                }
            }
        });
        holder.rvChildName.setAdapter(categoryAdapterTwo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_child_category)
        TextView txtChildCategory;
        @BindView(R.id.rv_child_name)
        RecyclerView rvChildName;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
