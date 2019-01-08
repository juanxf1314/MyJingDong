package com.bwie.juan_mao.jingdong_kanglijuan.view;

import com.bwie.juan_mao.jingdong_kanglijuan.base.IView;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface ICartView extends IView {

    void getCarts(MessageBean<List<ShopperBean<List<ProductBean>>>> data);

    void getRecommend(RecommendBean data);

    void deleteCart(RegisterBean data);

    void getDefaultAddr(DefaultAddrBean data);

    void createOrder(RegisterBean data);

    void updateCarts(RegisterBean data);

    void onCartFailed(Throwable t);
}
