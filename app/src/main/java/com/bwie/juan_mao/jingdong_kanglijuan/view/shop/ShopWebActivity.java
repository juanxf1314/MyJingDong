package com.bwie.juan_mao.jingdong_kanglijuan.view.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.HomeActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.ClassifyPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IClassifyView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopWebActivity extends BaseActivity<ClassifyPresenter> implements IClassifyView {

    @BindView(R.id.wv_shop_web)
    WebView wvShopWeb;
    @BindView(R.id.img_jump_cart)
    ImageView imgJumpCart;
    @BindView(R.id.btn_add_cart)
    Button btnAddCart;
    @BindView(R.id.btn_immediate_purchase)
    Button btnImmediatePurchase;
    private int pid;
    private boolean notEmpty = false;
    private List<ShopperBean<List<ProductBean>>> list;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 0);
        presenter.getProductDetail(pid);
    }

    @Override
    protected ClassifyPresenter providePresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_shop_web;
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getCategory(CategoryBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getProductCategory(ProductCategoryBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getShopList(ShopListBean data) {

    }

    @Override
    public void addCart(RegisterBean data) {
        // 加入购物车成功
        Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CartActivity.class));
    }

    @Override
    public void getProductDetail(ProductDetailsBean data) {
        notEmpty = true;
        ProductDetailsBean.DataBean productDetails = data.getData();
        wvShopWeb.loadUrl(Https2http.replace(productDetails.getDetailUrl()));
        wvShopWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        // 自己创建一个shopperBean
        ProductBean productBean = new ProductBean(productDetails.getImages(), 1, productDetails.getPrice(), productDetails.getTitle());
        List<ProductBean> productList = new ArrayList<>();
        productList.add(productBean);
        ShopperBean<List<ProductBean>> listShopperBean = new ShopperBean<>();
        listShopperBean.setList(productList);
        listShopperBean.setSellerName(data.getSeller().getName());
        list = new ArrayList<>();
        list.add(listShopperBean);
    }

    @Override
    public void onClassifyFailed(Throwable t) {
        notEmpty = false;
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_jump_cart, R.id.btn_add_cart, R.id.btn_immediate_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_jump_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.btn_add_cart:
                presenter.addCart(pid);
                break;
            case R.id.btn_immediate_purchase:
                if (notEmpty) {
                    Intent intent = new Intent(this, PlaceOrderActivity.class);
                    intent.putExtra("list", (Serializable) list);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "该商品暂时无法购买", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
