package com.bwie.juan_mao.jingdong_kanglijuan.contact;


import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IMineApi {

    @GET("user/getUserInfo")
    Observable<UserInfoBean> getUserInfo(@Query("uid") int uid/*, @Query("token") String token*/);

    @GET("product/getCarts")
    Observable<RecommendBean> getRecommend(@Query("uid") int uid);

    @GET("user/login")
    Observable<UserInfoBean> login(@Query("mobile") String mobile, @Query("password") String password);

    @GET("user/reg")
    Observable<RegisterBean> register(@Query("mobile") String mobile, @Query("password") String password);

    @GET("user/addAddr")
    Observable<RegisterBean> addAddress(@Query("uid") int uid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name/*, @Query("token") String token*/);

    @GET("user/updateAddr")
    Observable<UpdateAddressBean> updateAddress(@Query("uid") int uid, @Query("addrid") int addrid, @Query("mobile") String mobile, @Query("name") String name/*, @Query("token") String token*/);

    @GET("user/updateNickName")
    Observable<RegisterBean> updateNickname(@Query("uid") int uid, @Query("nickname") String nickname/*, @Query("token") String token*/);

    @GET("user/getAddrs")
    Observable<AddressBean> getAddress(@Query("uid") int uid/*, @Query("token") String token*/);

    @Multipart
    @POST("file/upload")
    Observable<RegisterBean> upload(@Query("uid") int uid, @Part MultipartBody.Part part);


}
