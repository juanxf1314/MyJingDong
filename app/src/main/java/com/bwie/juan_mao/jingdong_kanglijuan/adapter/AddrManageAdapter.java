package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/10/18.
 */

public class AddrManageAdapter extends RecyclerView.Adapter<AddrManageAdapter.MyViewHolder> {
    private Context context;
    private List<AddressBean.DataBean> list;

    public AddrManageAdapter(Context context, List<AddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnEditClickListener {
        void onEditClick(long addrid, int position);
    }

    private OnEditClickListener editListener;

    public void setOnEditClickListener(OnEditClickListener editClickListener) {
        this.editListener = editClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_addr_manage, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtName.setText(list.get(position).getName());
        long mobile = list.get(position).getMobile();
        if (String.valueOf(mobile).length() >= 8) {
            String strPre = String.valueOf(mobile).substring(0, 3);
            String strHou = String.valueOf(mobile).substring(7, String.valueOf(mobile).length());
            holder.txtPhone.setText(strPre + "****" + strHou);
        } else {
            holder.txtPhone.setText(mobile + "");
        }
        if (list.get(position).getStatus() == 1) {
            holder.txtDefault.setVisibility(View.VISIBLE);
        } else {
            holder.txtDefault.setVisibility(View.GONE);
        }
        holder.txtAddress.setText(list.get(position).getAddr());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListener.onEditClick(list.get(position).getAddrid(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_phone)
        TextView txtPhone;
        @BindView(R.id.txt_default)
        TextView txtDefault;
        @BindView(R.id.txt_address)
        TextView txtAddress;
        @BindView(R.id.img_edit)
        ImageView imgEdit;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
