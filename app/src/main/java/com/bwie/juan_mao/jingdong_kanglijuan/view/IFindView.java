package com.bwie.juan_mao.jingdong_kanglijuan.view;

import com.bwie.juan_mao.jingdong_kanglijuan.base.IView;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IFindView extends IView {

    void getNews(NewsBean data);

    void onFindFailed(Throwable t);
}
