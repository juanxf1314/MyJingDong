package com.bwie.juan_mao.jingdong_kanglijuan.model;


import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.contact.IFindApi;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class FindModel {

    public Observable<NewsBean> getNews(String url, String type, int page) {
        IFindApi iFindApi = RetrofitManager.getInstance().create(IFindApi.class);
        return iFindApi.getNews(url, type, page);
    }

}
