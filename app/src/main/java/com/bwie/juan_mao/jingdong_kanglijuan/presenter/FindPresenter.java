package com.bwie.juan_mao.jingdong_kanglijuan.presenter;

import android.util.Log;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.model.FindModel;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IClassifyView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IFindView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public class FindPresenter extends BasePresenter<IFindView> {

    private FindModel model;

    @Override
    protected void initModel() {
        model = new FindModel();
    }

    public void getNews(String url, String type, int page) {
        model.getNews(url, type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                        if (newsBean != null && newsBean.getCode() == 1) {
                            if (iView != null) {
                                iView.getNews(newsBean);
                                return;
                            }
                            if (iView != null) {
                                iView.onFindFailed(new Throwable(newsBean.getMsg()));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onFindFailed(new Throwable("网络异常"));
                        }
                    }
                });
    }

}
