package com.bwie.juan_mao.jingdong_kanglijuan.base;

import android.content.Context;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public abstract class BasePresenter<V extends IView> {

    protected V iView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void attach(V iView) {
        this.iView = iView;
    }

    public void detach() {
        if (iView != null) {
            iView = null;
        }
    }

    public Context getContext() {
        if (iView != null) {
            return iView.getContext();
        }
        return null;
    }
}
