package com.bwie.juan_mao.jingdong_kanglijuan.view.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.OrderShopperAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.CartPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.ICartView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceOrderActivity extends BaseActivity<CartPresenter> implements ICartView {

    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.txt_name_phone)
    TextView txtNamePhone;
    @BindView(R.id.txt_default)
    TextView txtDefault;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.ll_change_address)
    LinearLayout llChangeAddress;
    @BindView(R.id.txt_tip)
    TextView txtTip;
    @BindView(R.id.rv_shopper)
    RecyclerView rvShopper;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.btn_submit_order)
    Button btnSubmitOrder;
    private List<ShopperBean<List<ProductBean>>> list;
    private float totalPrice;

    @Override
    protected void initData() {
        rvShopper.setNestedScrollingEnabled(false);
        presenter.getDefaultAddr();

        Intent intent = getIntent();
        list = (List<ShopperBean<List<ProductBean>>>) intent.getSerializableExtra("list");
        OrderShopperAdapter adapter = new OrderShopperAdapter(this, list);
        rvShopper.setLayoutManager(new LinearLayoutManager(this));
        rvShopper.setAdapter(adapter);

        // 计算总价
        totalPrice = 0;
        for (ShopperBean<List<ProductBean>> listShopperBean : list) {
            for (ProductBean productBean : listShopperBean.getList()) {
                totalPrice += productBean.getPrice() * productBean.getNum();
            }
        }
        txtTotalPrice.setText("￥" + totalPrice);
    }

    @Override
    protected CartPresenter providePresenter() {
        return new CartPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_place_order;
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
    public void getCarts(MessageBean<List<ShopperBean<List<ProductBean>>>> data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getRecommend(RecommendBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void deleteCart(RegisterBean data) {

    }

    @Override
    public void getDefaultAddr(DefaultAddrBean data) {
        txtNamePhone.setText(data.getData().getName() + "\t\t" + data.getData().getMobile());
        txtAddress.setText(data.getData().getAddr());
    }

    @Override
    public void createOrder(RegisterBean data) {
        // 下单成功
        Toast.makeText(this, "下单成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void updateCarts(RegisterBean data) {

    }

    @Override
    public void onCartFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
//        txtTip.setVisibility(View.VISIBLE);
//        llChangeAddress.setVisibility(View.GONE);
    }

    @OnClick({R.id.txt_back, R.id.ll_change_address, R.id.btn_submit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.ll_change_address:
                break;
            case R.id.btn_submit_order:
                presenter.createOrder(totalPrice);
                break;
        }
    }

}
