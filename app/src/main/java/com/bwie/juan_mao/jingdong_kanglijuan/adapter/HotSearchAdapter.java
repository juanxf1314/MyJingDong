package com.bwie.juan_mao.jingdong_kanglijuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/11/15.
 */

public class HotSearchAdapter extends RecyclerView.Adapter<HotSearchAdapter.ViewHolder> {

    private Context context;
    private List<String> list;

    public HotSearchAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnHotItemClickListener {
        void onHotClick(View view, int position);
    }

    private OnHotItemClickListener onHotItemClickListener;

    public void setOnHotItemClickListener(OnHotItemClickListener onHotItemClickListener) {
        this.onHotItemClickListener = onHotItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_hot_search, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtHotSearch.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHotItemClickListener != null) {
                    onHotItemClickListener.onHotClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_hot_search)
        TextView txtHotSearch;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
