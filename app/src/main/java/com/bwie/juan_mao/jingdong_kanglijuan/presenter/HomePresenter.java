package com.bwie.juan_mao.jingdong_kanglijuan.presenter;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.model.HomeModel;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IHomeView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    private HomeModel model;

    @Override
    protected void initModel() {
        model = new HomeModel();
    }

    public void getBannerAndSecKill() {
        model.getBannerAndSecKill()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerAndSecKillBean>() {
                    @Override
                    public void accept(BannerAndSecKillBean bannerAndSecKillBean) throws Exception {
                        if (bannerAndSecKillBean != null && "0".equals(bannerAndSecKillBean.getCode())) {
                            if (iView != null) {
                                iView.getBannerAndSecKill(bannerAndSecKillBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onHomeFailed(new Throwable(bannerAndSecKillBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onHomeFailed(new Throwable("网络异常"));
                        }
                    }
                });
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
                                iView.onHomeFailed(new Throwable(categoryBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onHomeFailed(new Throwable("网络异常"));
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
                                iView.onHomeFailed(new Throwable(recommendBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onHomeFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }


}
