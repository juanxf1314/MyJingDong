package com.bwie.juan_mao.jingdong_kanglijuan.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.model.MineModel;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class MinePresenter extends BasePresenter<IMineView> {

    private MineModel model;

    @Override
    protected void initModel() {
        model = new MineModel();
    }

    /**
     * 校验登录
     */
    public void check() {
        String username = iView.getUsername();
        String password = iView.getPassword();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            iView.check(false, "用户名或密码不为空");
        } else if (username.length() != 11 && !username.substring(0, 1).equals("1")) {
            iView.check(false, "手机号格式不正确");
        } else if (password.length() < 6) {
            iView.check(false, "密码格式有问题，不能少于6位数");
        } else {
            iView.check(true, "验证通过");
        }
    }

    /**
     * 校验注册
     */
    public void checkRegister() {
        String username = iView.getUsername();
        String password = iView.getPassword();
        String password2 = iView.getPassword2();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            iView.check(false, "用户名或密码不能为空");
        } else if (!password.equals(password2)) {
            iView.check(false, "两次密码不一致");
        } else if (username.length() != 11 && !username.substring(0, 1).equals("1")) {
            iView.check(false, "手机号格式不正确");
        } else if (password.length() < 6) {
            iView.check(false, "密码格式有问题，不能少于6位数");
        } else {
            iView.check(true, "验证通过");
        }
    }

    public void getRecommend(int uid) {
        model.getRecommend(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecommendBean>() {
                    @Override
                    public void accept(RecommendBean recommendBean) throws Exception {
                        if (recommendBean != null && "0".equals(recommendBean.getCode())) {
                            if (iView != null) {
                                iView.getRecommend(recommendBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(recommendBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getUserInfo() {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        if (userInfoBean != null && "0".equals(userInfoBean.getCode())) {
                            if (iView != null) {
                                iView.getUserInfo(userInfoBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(userInfoBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void login() {
        model.login(iView.getUsername(), iView.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        if (userInfoBean != null && "0".equals(userInfoBean.getCode())) {
                            if (iView != null) {
                                iView.getUserInfo(userInfoBean);
                                SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
                                sp.edit().putBoolean("isLogin", true)
                                        .putString("token", userInfoBean.getData().getToken())
                                        .putInt("uid", userInfoBean.getData().getUid())
                                        .apply();
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(userInfoBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void register() {
        model.register(iView.getUsername(), iView.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.register(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void addAddress(String addr, String mobile, String name) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.addAddress(uid, addr, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.register(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void updateAddress(int addrid, String mobile, String name) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.updateAddress(uid, addrid, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateAddressBean>() {
                    @Override
                    public void accept(UpdateAddressBean updateAddressBean) throws Exception {
                        if (updateAddressBean != null && "0".equals(updateAddressBean.getCode())) {
                            if (iView != null) {
                                iView.updateAddress(updateAddressBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(updateAddressBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("123", "accept: " + throwable.getMessage());
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void updateNickname(String nickname) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.updateNickname(uid, nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.register(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getAddress() {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.getAddress(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddressBean>() {
                    @Override
                    public void accept(AddressBean addressBean) throws Exception {
                        if (addressBean != null && "0".equals(addressBean.getCode())) {
                            if (iView != null) {
                                iView.getAddress(addressBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(addressBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    /**
     * 这里的上传头像的bean类跟Register是一样的，复用
     *
     * @param file
     */
    public void upload(File file) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.upload(uid, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.register(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onMineFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onMineFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onMineFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }
}
