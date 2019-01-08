package com.bwie.juan_mao.jingdong_kanglijuan.contact;


import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IFindApi {

    @GET()
    Observable<NewsBean> getNews(@Url String url, @Query("type") String type, @Query("page") int page);

}
