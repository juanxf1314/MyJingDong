package com.bwie.juan_mao.jingdong_kanglijuan.view;

import com.bwie.juan_mao.jingdong_kanglijuan.base.IView;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;

import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IClassifyView extends IView {

    void getCategory(CategoryBean data);

    void getProductCategory(ProductCategoryBean data);

    void getShopList(ShopListBean data);

    void addCart(RegisterBean data);

    void getProductDetail(ProductDetailsBean data);

    void onClassifyFailed(Throwable t);
}
