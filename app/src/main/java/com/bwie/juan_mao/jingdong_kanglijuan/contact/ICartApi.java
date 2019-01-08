package com.bwie.juan_mao.jingdong_kanglijuan.contact;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface ICartApi {

    @GET("product/getCarts")
    Observable<MessageBean<List<ShopperBean<List<ProductBean>>>>> getCarts(@Query("uid") int uid);

    @GET("product/getCarts")
    Observable<RecommendBean> getRecommend(@Query("uid") int uid);

    @GET("product/deleteCart")
    Observable<RegisterBean> deleteCart(@Query("uid") int uid, @Query("pid") int pid);

    @GET("user/getDefaultAddr")
    Observable<DefaultAddrBean> getDefaultAddr(@Query("uid") int uid);

    @GET("product/createOrder")
    Observable<RegisterBean> createOrder(@Query("uid") int uid, @Query("price") float price);

    @GET("product/updateCarts")
    Observable<RegisterBean> updateCarts(@Query("uid") int uid, @Query("sellerid") int sellerid, @Query("pid") int pid, @Query("selected") int selected, @Query("num") int num);


}
