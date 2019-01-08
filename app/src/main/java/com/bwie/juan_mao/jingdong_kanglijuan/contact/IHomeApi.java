package com.bwie.juan_mao.jingdong_kanglijuan.contact;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IHomeApi {

    @GET("ad/getAd")
    Observable<BannerAndSecKillBean> getBannerAndSecKill();

    @GET("product/getCatagory")
    Observable<CategoryBean> getCategory();

    @GET("product/getCarts")
    Observable<RecommendBean> getRecommend(@Query("uid") int uid);
}
