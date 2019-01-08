package com.bwie.juan_mao.jingdong_kanglijuan.view.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.MinePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAddressActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.txt_receiver)
    EditText txtReceiver;
    @BindView(R.id.txt_mobile)
    EditText txtMobile;
    @BindView(R.id.txt_area)
    EditText txtArea;
    @BindView(R.id.txt_detailed_address)
    EditText txtDetailedAddress;
    @BindView(R.id.ll_save)
    LinearLayout llSave;
    private long addrid;

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
    public void updateAddress(UpdateAddressBean data) {
        Toast.makeText(this, data.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getAddress(AddressBean data) {

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
     * 用不到
     *
     * @return
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 用不到
     *
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * 用不到
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
    protected void initData() {

    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        addrid = intent.getLongExtra("addrid", 0);
        AddressBean.DataBean bean = (AddressBean.DataBean) intent.getSerializableExtra("bean");
        txtReceiver.setText(bean.getName());
        txtMobile.setText(bean.getMobile() + "");
        if (bean.getAddr().contains("市")) {
            String[] split = bean.getAddr().split("市");
            txtArea.setText(split[0] + "市");
            txtDetailedAddress.setText(split[1]);
        } else {
            txtArea.setText(bean.getAddr());
            txtDetailedAddress.setText(bean.getAddr());
        }
    }

    @OnClick({R.id.txt_back, R.id.ll_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.ll_save:
                String name = txtReceiver.getText().toString().trim();
                String mobile = txtMobile.getText().toString().trim();
                String area = txtArea.getText().toString();
                String detailed = txtDetailedAddress.getText().toString();
                presenter.updateAddress((int) addrid, mobile, name);
                break;
        }
    }

    /*private ImageView txtBack;
    private EditText txtReceiver;
    private EditText txtMobile;
    private EditText txtArea;
    private EditText txtDetailedAddress;
    private LinearLayout llSave;
    private AddrPresenter addrPresenter;
    private long addrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        setContentView(R.layout.activity_edit_address);
        initView();

        initData();
    }

    private void initData() {
        addrPresenter = new AddrPresenter();
        addrPresenter.attach(this);
    }

    private void initView() {
        txtBack = findViewById(R.id.txt_back);
        txtReceiver = findViewById(R.id.txt_receiver);
        txtMobile = findViewById(R.id.txt_mobile);
        txtArea = findViewById(R.id.txt_area);
        txtDetailedAddress = findViewById(R.id.txt_detailed_address);
        llSave = findViewById(R.id.ll_save);

        txtBack.setOnClickListener(this);
        llSave.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        addrid = intent.getLongExtra("addrid", 0);
        AddressBean.DataBean bean = (AddressBean.DataBean) intent.getSerializableExtra("bean");
        txtReceiver.setText(bean.getName());
        txtMobile.setText(bean.getMobile() + "");
        if (bean.getAddr().contains("市")) {
            String[] split = bean.getAddr().split("市");
            txtArea.setText(split[0] + "市");
            txtDetailedAddress.setText(split[1]);
        } else {
            txtArea.setText(bean.getAddr());
            txtDetailedAddress.setText(bean.getAddr());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.ll_save:
                String name = txtReceiver.getText().toString().trim();
                String mobile = txtMobile.getText().toString().trim();
                String area = txtArea.getText().toString();
                String detailed = txtDetailedAddress.getText().toString();
                addrPresenter.editAddress("http://www.zhaoapi.cn/user/updateAddr",
                        name, mobile, area + " " + detailed, (int) addrid);
                break;
        }
    }

    @Override
    public void successAddr(ChangeAddress changeAddress) {
        if (Integer.parseInt(changeAddress.getCode()) == 0) {
            Toast.makeText(this, changeAddress.getMsg(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void failedAddr(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }*/
}
