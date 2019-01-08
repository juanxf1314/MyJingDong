package com.bwie.juan_mao.jingdong_kanglijuan.view.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.AddrManageAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.MinePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressManageActivity extends BaseActivity<MinePresenter> implements IMineView {

    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.rv_addr)
    RecyclerView rvAddr;
    @BindView(R.id.ll_new_addr)
    LinearLayout llNewAddr;
    private List<AddressBean.DataBean> list;
    private AddrManageAdapter addrManageAdapter;

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void updateAddress(UpdateAddressBean data) {

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
    public void getUserInfo(UserInfoBean data) {

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
    public void register(RegisterBean data) {

    }

    @Override
    public void getAddress(AddressBean data) {
        List<AddressBean.DataBean> dataBeans = data.getData();
        if (dataBeans != null) {
            list.clear();
            list.addAll(dataBeans);
            addrManageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 用不到
     *
     * @param isChecked
     * @param msg
     */
    @Override
    public void check(boolean isChecked, String msg) {

    }

    /**
     * 用不着
     *
     * @return
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 用不着
     *
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * 用不着
     *
     * @return
     */
    @Override
    public String getPassword2() {
        return null;
    }

    @Override
    public void onMineFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAddress();
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        addrManageAdapter = new AddrManageAdapter(this, list);
        rvAddr.setLayoutManager(new LinearLayoutManager(this));
        rvAddr.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAddr.setAdapter(addrManageAdapter);
        addrManageAdapter.setOnEditClickListener(new AddrManageAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(long addrid, int position) {
                AddressBean.DataBean dataBean = list.get(position);
                Intent intent = new Intent(AddressManageActivity.this, EditAddressActivity.class);
                intent.putExtra("bean", dataBean);
                intent.putExtra("addrid", addrid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_address_manage;
    }

    @OnClick({R.id.txt_back, R.id.ll_new_addr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.ll_new_addr:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    /*private ImageView txtBack;
    private RecyclerView rvAddr;
    private LinearLayout llNewAddr;
    private List<AddressBean.DataBean> list;
    private AddrManageAdapter addrManageAdapter;
    private AddrPresenter addrPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        setContentView(R.layout.activity_address_manage);
        initView();

        initData();
    }

    private void initData() {
        addrPresenter = new AddrPresenter();
        addrPresenter.attach(this);

        list = new ArrayList<>();
        addrManageAdapter = new AddrManageAdapter(this, list);
        rvAddr.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAddr.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAddr.setAdapter(addrManageAdapter);

        addrManageAdapter.setOnEditClickListener(new AddrManageAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(long addrid, int position) {
                AddressBean.DataBean dataBean = list.get(position);
                Intent intent = new Intent(AddressManageActivity.this, EditAddressActivity.class);
                intent.putExtra("bean", dataBean);
                intent.putExtra("addrid", addrid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addrPresenter.getAddress("http://www.zhaoapi.cn/user/getAddrs");
    }

    private void initView() {
        txtBack = findViewById(R.id.txt_back);
        rvAddr = findViewById(R.id.rv_addr);
        llNewAddr = findViewById(R.id.ll_new_addr);

        txtBack.setOnClickListener(this);
        llNewAddr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.ll_new_addr:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    public void successAddr(AddressBean addressBean) {
        if (addressBean != null) {
            List<AddressBean.DataBean> data = addressBean.getData();
            if (data != null) {
                list.clear();
                list.addAll(data);
                addrManageAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failedAddr(Exception e) {

    }

    @Override
    public Context getContext() {
        return this;
    }*/
}
