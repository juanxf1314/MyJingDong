package com.bwie.juan_mao.jingdong_kanglijuan.view;

import com.bwie.juan_mao.jingdong_kanglijuan.base.IView;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IHomeView extends IView {

    void getBannerAndSecKill(BannerAndSecKillBean data);

    void getCategory(CategoryBean data);

    void getRecommend(RecommendBean data);

    void getCarts(MessageBean<List<ShopperBean<List<ProductBean>>>> data);

    void onHomeFailed(Throwable t);
}
