package com.bwie.juan_mao.jingdong_kanglijuan.model;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.contact.IHomeApi;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class HomeModel {

    public Observable<BannerAndSecKillBean> getBannerAndSecKill() {
        IHomeApi iHomeApi = RetrofitManager.getInstance().create(IHomeApi.class);
        return iHomeApi.getBannerAndSecKill();
    }

    public Observable<CategoryBean> getCategory() {
        IHomeApi iHomeApi = RetrofitManager.getInstance().create(IHomeApi.class);
        return iHomeApi.getCategory();
    }

    public Observable<RecommendBean> getRecommend(int uid) {
        IHomeApi iHomeApi = RetrofitManager.getInstance().create(IHomeApi.class);
        return iHomeApi.getRecommend(uid);
    }

}
