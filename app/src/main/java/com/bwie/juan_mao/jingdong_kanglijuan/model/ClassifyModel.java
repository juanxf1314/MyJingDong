package com.bwie.juan_mao.jingdong_kanglijuan.model;


import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.contact.IClassifyApi;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class ClassifyModel {

    public Observable<CategoryBean> getCategory() {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.getCategory();
    }

    public Observable<ProductCategoryBean> getProductCategory(int cid) {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.getProductCategory(cid);
    }

    public Observable<ShopListBean> searchProducts(String keywords, int page) {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.searchProducts(keywords, page);
    }

    public Observable<ShopListBean> getProducts(int pscid, int page) {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.getProducts(pscid, page);
    }

    public Observable<ProductDetailsBean> getProductDetail(int pid) {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.getProductDetail(pid);
    }

    public Observable<RegisterBean> addCart(int pid, int uid) {
        IClassifyApi iClassifyApi = RetrofitManager.getInstance().create(IClassifyApi.class);
        return iClassifyApi.addCart(pid, uid);
    }
}
