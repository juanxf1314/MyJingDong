package com.bwie.juan_mao.jingdong_kanglijuan.view;

import com.bwie.juan_mao.jingdong_kanglijuan.base.IView;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;

import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IMineView extends IView {

    void getUserInfo(UserInfoBean data);

    void getRecommend(RecommendBean data);

    void register(RegisterBean data);

    void updateAddress(UpdateAddressBean data);

    void getAddress(AddressBean data);

    void check(boolean isChecked, String msg);

    String getUsername();

    String getPassword();

    String getPassword2();

    void onMineFailed(Throwable t);
}
