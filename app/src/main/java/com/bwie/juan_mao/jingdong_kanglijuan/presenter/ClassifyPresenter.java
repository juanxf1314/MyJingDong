package com.bwie.juan_mao.jingdong_kanglijuan.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
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
import com.bwie.juan_mao.jingdong_kanglijuan.model.ClassifyModel;
import com.bwie.juan_mao.jingdong_kanglijuan.model.HomeModel;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IClassifyView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IHomeView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class ClassifyPresenter extends BasePresenter<IClassifyView> {

    private ClassifyModel model;

    @Override
    protected void initModel() {
        model = new ClassifyModel();
    }

    public void getCategory() {
        model.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryBean>() {
                    @Override
                    public void accept(CategoryBean categoryBean) throws Exception {
                        if (categoryBean != null && "0".equals(categoryBean.getCode())) {
                            if (iView != null) {
                                iView.getCategory(categoryBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(categoryBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getProductCategory(int cid) {
        model.getProductCategory(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductCategoryBean>() {
                    @Override
                    public void accept(ProductCategoryBean productCategoryBean) throws Exception {
                        if (productCategoryBean != null && "0".equals(productCategoryBean.getCode())) {
                            if (iView != null) {
                                iView.getProductCategory(productCategoryBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(productCategoryBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void searchProducts(String keywords, int page) {
        model.searchProducts(keywords, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopListBean>() {
                    @Override
                    public void accept(ShopListBean shopListBean) throws Exception {
                        if (shopListBean != null && "0".equals(shopListBean.getCode())) {
                            if (iView != null) {
                                iView.getShopList(shopListBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(shopListBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getProducts(int pscid, int page) {
        model.getProducts(pscid, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopListBean>() {
                    @Override
                    public void accept(ShopListBean shopListBean) throws Exception {
                        if (shopListBean != null && "0".equals(shopListBean.getCode())) {
                            if (iView != null) {
                                iView.getShopList(shopListBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(shopListBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void getProductDetail(int pid) {
        model.getProductDetail(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductDetailsBean>() {
                    @Override
                    public void accept(ProductDetailsBean productDetailsBean) throws Exception {
                        if (productDetailsBean != null && "0".equals(productDetailsBean.getCode())) {
                            if (iView != null) {
                                iView.getProductDetail(productDetailsBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(productDetailsBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("message", "accept: " + throwable.getMessage());
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

    public void addCart(int pid) {
        // 获取用户登录时的token和uid
        SharedPreferences sp = iView.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        int uid = sp.getInt("uid", 0);
        model.addCart(pid, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean != null && "0".equals(registerBean.getCode())) {
                            if (iView != null) {
                                iView.addCart(registerBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onClassifyFailed(new Throwable(registerBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onClassifyFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

}
