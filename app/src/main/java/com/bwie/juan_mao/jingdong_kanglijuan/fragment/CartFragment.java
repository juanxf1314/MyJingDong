package com.bwie.juan_mao.jingdong_kanglijuan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.ProductAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.RecommendShopAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.ShopperAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DefaultAddrBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.CartPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.ICartView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.PlaceOrderActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.ShopWebActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 卷猫~ on 2018/10/12.
 */

public class CartFragment extends BaseFragment<CartPresenter> implements ICartView {

    @BindView(R.id.rv_shopper)
    RecyclerView rvShopper;
    @BindView(R.id.cb_checkall)
    CheckBox cbCheckall;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.txt_edit_complete)
    TextView txtEditComplete;
    @BindView(R.id.img_msg)
    ImageView imgMsg;
    @BindView(R.id.img_recommend)
    ImageView imgRecommend;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.txt_delete)
    TextView txtDelete;
    @BindView(R.id.btn_account)
    Button btnAccount;
    private List<ShopperBean<List<ProductBean>>> cartList;
    private ShopperAdapter adapter;
    private boolean isEdited = false;
    private List<RecommendBean.DataBean.ListBean> list;
    private RecommendShopAdapter recommendShopAdapter;
    private TextView txtTip;
    private Button btnCancel;
    private Button btnConfirm;
    private AlertDialog alertDialog;

    @Override
    protected void initData() {
        // 设置弹框
        View view = View.inflate(getActivity(), R.layout.dialog_delete_cart, null);
        txtTip = view.findViewById(R.id.txt_tip);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();

        // 设置嵌套滑动
        rvRecommend.setNestedScrollingEnabled(false);
        rvShopper.setNestedScrollingEnabled(false);

        list = new ArrayList<>();
        rvRecommend.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvRecommend.setNestedScrollingEnabled(false);
        recommendShopAdapter = new RecommendShopAdapter(getActivity(), list);
        rvRecommend.setAdapter(recommendShopAdapter);
        recommendShopAdapter.setOnItemClickListener(new RecommendShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RecommendBean.DataBean.ListBean listBean = list.get(position);
                Intent intent = new Intent(getActivity(), ShopWebActivity.class);
                intent.putExtra("pid", listBean.getPid());
                startActivity(intent);
            }
        });

        cartList = new ArrayList<>();
        // 商家的列表
        adapter = new ShopperAdapter(getActivity(), cartList);
        // 添加一级条目（商家）状态发生变化时
        adapter.setOnShopperClickListener(new ShopperAdapter.OnShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                // 为了效率考虑，当点击状态变成未选中时，全选按钮肯定就不是全选了，就不用再循环一次
                if (!isCheck) {
                    cbCheckall.setChecked(false);
                } else {
                    // 如果是商家变成选中状态时，需要循环遍历所有的商家是否被选中
                    // 循环遍历之前先设置一个true标志位，只要有一个是未选中就改变这个标志位为false
                    boolean isAllShopperChecked = true;
                    for (ShopperBean<List<ProductBean>> listShopper : cartList) {
                        // 只要有一个商家没有被选中，全选复选框就变成未选中状态，并且结束循环
                        if (!listShopper.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbCheckall.setChecked(isAllShopperChecked);
                }

                // 一级条目发生变化时，计算一下总价
                calculatePrice();
            }
        });
        /*
        adapter.setOnProductSelectedChangeListener(new ShopperAdapter.OnProductSelectedChangeListener() {
            @Override
            public void onChange(int sellerid, int pid, int num, int selected) {
                Log.d("123456", "sellerid: " + sellerid + "--- pid: " + pid + "--- num: " + num + "--- selected: " + selected);
                presenter.updateCarts(sellerid, pid, num, selected);
            }
        });*/


        adapter.setOnAddDecreaseProductListener(new ProductAdapter.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShopper.setLayoutManager(layoutManager);
        rvShopper.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvShopper.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCarts();
        presenter.getRecommend(71);
        cbCheckall.setChecked(false);
        changeView();
    }

    private void changeView() {
        if (isEdited) {
            txtEditComplete.setText("完成");
            txtTotalPrice.setVisibility(View.GONE);
            txtDelete.setVisibility(View.VISIBLE);
            imgMsg.setVisibility(View.INVISIBLE);
            imgRecommend.setVisibility(View.GONE);
            rvRecommend.setVisibility(View.GONE);
            btnAccount.setVisibility(View.GONE);
        } else {
            txtEditComplete.setText("编辑");
            txtTotalPrice.setVisibility(View.VISIBLE);
            txtDelete.setVisibility(View.GONE);
            imgMsg.setVisibility(View.VISIBLE);
            imgRecommend.setVisibility(View.VISIBLE);
            rvRecommend.setVisibility(View.VISIBLE);
            btnAccount.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        cbCheckall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = cbCheckall.isChecked();
                // 遍历一级列表，和下方的全选状态一致
                for (ShopperBean<List<ProductBean>> listShopper : cartList) {
                    listShopper.setChecked(isChecked);
                    // 遍历二级列表，和下方的全选状态一致
                    List<ProductBean> products = listShopper.getList();
                    for (ProductBean product : products) {
                        product.setChecked(isChecked);
                    }
                }
                calculatePrice();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected CartPresenter providePresenter() {
        return new CartPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_cart;
    }

    // 计算商品总价
    private void calculatePrice() {
        // 遍历商家
        float totalPrice = 0;
        for (ShopperBean<List<ProductBean>> listShopper : cartList) {
            // 遍历商家的商品
            List<ProductBean> list = listShopper.getList();
            for (ProductBean product : list) {
                // 如果商品被选中
                if (product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }

        txtTotalPrice.setText("总价：￥" + totalPrice);

    }

    @Override
    public void getCarts(MessageBean<List<ShopperBean<List<ProductBean>>>> data) {
        // 获取商家列表
        List<ShopperBean<List<ProductBean>>> shoppers = data.getData();
        if (shoppers != null) {
            cartList.clear();
            cartList.addAll(shoppers);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getRecommend(RecommendBean data) {
        list.clear();
        for (int i = 0; i < data.getData().size(); i++) {
            for (int j = 0; j < data.getData().get(i).getList().size(); j++) {
                List<RecommendBean.DataBean.ListBean> listBeans = data.getData().get(i).getList();
                list.addAll(listBeans);
            }
        }
        recommendShopAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCart(RegisterBean data) {
        // 删除成功
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getDefaultAddr(DefaultAddrBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void createOrder(RegisterBean data) {

    }

    @Override
    public void updateCarts(RegisterBean data) {
        // 修改购物车成功
    }

    @Override
    public void onCartFailed(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.txt_edit_complete, R.id.img_msg, R.id.txt_delete, R.id.btn_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_edit_complete:
                isEdited = !isEdited;
                changeView();
                break;
            case R.id.img_msg:
                Toast.makeText(getActivity(), "此功能尚在开发中~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_delete:
                int count = 0;
                for (ShopperBean<List<ProductBean>> listShopper : cartList) {
                    // 遍历商家的商品
                    List<ProductBean> list = listShopper.getList();
                    for (ProductBean product : list) {
                        // 如果商品被选中
                        if (product.isChecked()) {
                            count++;
                        }
                    }
                }
                if (count != 0) {
                    txtTip.setText("确认要删除这" + count + "种商品吗？");
                    alertDialog.show();
                    // 如果确认就遍历删除
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (ShopperBean<List<ProductBean>> listShopper : cartList) {
                                // 遍历商家的商品
                                List<ProductBean> list = listShopper.getList();
                                for (ProductBean product : list) {
                                    // 如果商品被选中
                                    if (product.isChecked()) {
                                        presenter.deleteCart(product.getPid());
                                    }
                                }
                            }
                            isEdited = false;
                            changeView();
                            alertDialog.dismiss();
                            presenter.getCarts();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btn_account:
                List<ShopperBean<List<ProductBean>>> listBean = new ArrayList<>();
                for (ShopperBean<List<ProductBean>> listShopper : cartList) {
                    // 遍历商家的商品
                    if (listShopper.isChecked()) {
                        listBean.add(listShopper);
                    } else {
                        for (ProductBean productBean : listShopper.getList()) {
                            if (productBean.isChecked()) {
                                // 自己创建一个shopperBean
                                List<ProductBean> productList = new ArrayList<>();
                                productList.add(productBean);
                                ShopperBean<List<ProductBean>> listShopperBean = new ShopperBean<>();
                                listShopperBean.setList(productList);
                                listShopperBean.setSellerName(listShopper.getSellerName());
                                listBean.add(listShopperBean);
                            }
                        }
                    }
                }
                if (listBean.size() != 0) {
                    Intent intent = new Intent(getActivity(), PlaceOrderActivity.class);
                    intent.putExtra("list", (Serializable) listBean);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "请选中商品再下单", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
