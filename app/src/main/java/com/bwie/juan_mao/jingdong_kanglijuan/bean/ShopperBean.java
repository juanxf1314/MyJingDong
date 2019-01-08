package com.bwie.juan_mao.jingdong_kanglijuan.bean;

import java.io.Serializable;

/**
 * Created by 卷猫~ on 2018/10/26.
 */

public class ShopperBean<T> implements Serializable{
    private String sellerName;
    private String sellerid;
    private boolean isChecked;
    private T list;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
