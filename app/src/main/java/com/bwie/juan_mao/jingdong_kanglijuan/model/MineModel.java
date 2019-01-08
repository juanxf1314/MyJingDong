package com.bwie.juan_mao.jingdong_kanglijuan.model;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.contact.IMineApi;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.RetrofitManager;


import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class MineModel {

    public Observable<RecommendBean> getRecommend(int uid) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.getRecommend(uid);
    }

    public Observable<UserInfoBean> getUserInfo(int uid) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.getUserInfo(uid);
    }

    public Observable<UserInfoBean> login(String mobile, String password) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.login(mobile, password);
    }

    public Observable<RegisterBean> register(String mobile, String password) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.register(mobile, password);
    }

    public Observable<RegisterBean> addAddress(int uid, String addr, String mobile, String name) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.addAddress(uid, addr, mobile, name);
    }

    public Observable<UpdateAddressBean> updateAddress(int uid, int addrid, String mobile, String name) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.updateAddress(uid, addrid, mobile, name);
    }

    public Observable<RegisterBean> updateNickname(int uid, String nickname) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.updateNickname(uid, nickname);
    }

    public Observable<AddressBean> getAddress(int uid) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        return iMineApi.getAddress(uid);
    }

    /**
     * 上传头像
     *
     * @param uid
     * @param file
     * @return
     */
    public Observable<RegisterBean> upload(int uid, File file) {
        IMineApi iMineApi = RetrofitManager.getInstance().create(IMineApi.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return iMineApi.upload(uid, part);
    }
}
