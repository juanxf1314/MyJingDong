package com.bwie.juan_mao.jingdong_kanglijuan.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.model.CartModel;
import com.bwie.juan_mao.jingdong_kanglijuan.view.ICartView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class CartPresenter extends BasePresenter<ICartView> {

    private CartModel model;

    @Override
    protected void initModel() {
        model = new CartModel();
    }

    public void getCarts() {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.getCarts(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageBean<List<ShopperBean<List<ProductBean>>>>>() {
                    @Override
                    public void accept(MessageBean<List<ShopperBean<List<ProductBean>>>> listMessageBean) throws Exception {
                        if (listMessageBean != null && "0".equals(listMessageBean.getCode())) {
                            if (iView != null) {
                                iView.getCarts(listMessageBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(listMessageBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
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
                                iView.onCartFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(recommendBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void deleteCart(int pid) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.deleteCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.deleteCart(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onCartFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getDefaultAddr() {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.getDefaultAddr(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DefaultAddrBean>() {
                    @Override
                    public void accept(DefaultAddrBean defaultAddrBean) throws Exception {
                        if (defaultAddrBean != null && "0".equals(defaultAddrBean.getCode())) {
                            if (iView != null) {
                                iView.getDefaultAddr(defaultAddrBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onCartFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(defaultAddrBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void createOrder(float price) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.createOrder(uid, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.createOrder(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onCartFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void updateCarts(int sellerid, int pid, int selected, int num) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.updateCarts(uid, sellerid, pid, selected, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.updateCarts(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onCartFailed(new Throwable("网络未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onCartFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onCartFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }


}
