package com.bwie.juan_mao.jingdong_kanglijuan.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.juan_mao.jingdong_kanglijuan.R;

/**
 * Created by 卷猫~ on 2018/10/26.
 */

public class AddDecreaseWidget extends LinearLayout implements View.OnClickListener {
    private Button txtDecrease;
    private Button txtAdd;
    private TextView txtNum;
    private int num = 1;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        txtNum.setText(num + "");
        setColor();
    }

    public interface OnAddDecreaseClickListener {
        void onAddClick(int num);

        void onDecreaseClick(int num);
    }

    private OnAddDecreaseClickListener addDecreaseClickListener;

    public void setOnAddDecreaseClickListener(OnAddDecreaseClickListener onAddDecreaseClickListener) {
        this.addDecreaseClickListener = onAddDecreaseClickListener;
    }

    public AddDecreaseWidget(Context context) {
        this(context, null);
    }

    public AddDecreaseWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddDecreaseWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.widget_add_decrease, this);
        txtDecrease = findViewById(R.id.txt_decrease);
        txtAdd = findViewById(R.id.txt_add);
        txtNum = findViewById(R.id.txt_num);

        txtDecrease.setOnClickListener(this);
        txtAdd.setOnClickListener(this);
    }

    private void setColor() {
        if (Integer.parseInt(txtNum.getText().toString()) == 1) {
            txtDecrease.setTextColor(Color.rgb(121, 120, 120));
        } else {
            txtDecrease.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_decrease:
                if (num > 1) {
                    num--;
                }
                txtNum.setText(num + "");
                if (addDecreaseClickListener != null) {
                    addDecreaseClickListener.onDecreaseClick(num);
                }
                setColor();
                break;
            case R.id.txt_add:
                num++;
                txtNum.setText(num + "");
                if (addDecreaseClickListener != null) {
                    addDecreaseClickListener.onAddClick(num);
                }
                setColor();
                break;
        }
    }
}
