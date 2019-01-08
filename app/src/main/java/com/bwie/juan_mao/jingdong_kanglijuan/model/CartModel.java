package com.bwie.juan_mao.jingdong_kanglijuan.model;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.contact.ICartApi;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class CartModel {

    public Observable<MessageBean<List<ShopperBean<List<ProductBean>>>>> getCarts(int uid) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.getCarts(uid);
    }

    public Observable<RecommendBean> getRecommend(int uid) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.getRecommend(uid);
    }

    public Observable<RegisterBean> deleteCart(int uid, int pid) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.deleteCart(uid, pid);
    }

    public Observable<DefaultAddrBean> getDefaultAddr(int uid) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.getDefaultAddr(uid);
    }

    public Observable<RegisterBean> createOrder(int uid, float price) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.createOrder(uid, price);
    }

    public Observable<RegisterBean> updateCarts(int uid, int sellerid, int pid, int selected, int num) {
        ICartApi iCartApi = RetrofitManager.getInstance().create(ICartApi.class);
        return iCartApi.updateCarts(uid, sellerid, pid, selected, num);
    }
}
