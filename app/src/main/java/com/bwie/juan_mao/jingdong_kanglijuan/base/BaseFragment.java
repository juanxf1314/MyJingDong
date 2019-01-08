package com.bwie.juan_mao.jingdong_kanglijuan.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = providePresenter();
        attachIView();
        initView();
        setListener();
        initData();
    }

    protected abstract void initData();

    protected void setListener() {

    }

    private void initView() {

    }

    protected void attachIView() {
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            ImmersionBar.with(this).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ImmersionBar.with(this).destroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
