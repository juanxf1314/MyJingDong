package com.bwie.juan_mao.jingdong_kanglijuan.view.user;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.MinePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.address.AddressManageActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_username2)
    TextView txtUsername2;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.txt_back_login)
    TextView txtBackLogin;
    @BindView(R.id.img_logo)
    SimpleDraweeView imgLogo;
    private AlertDialog alertDialog;
    private Button btnCancel;
    private Button btnConfirm;

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

    @Override
    public void getUserInfo(UserInfoBean data) {
        UserInfoBean.DataBean dataBean = data.getData();
        if (dataBean.getNickname() != null) {
            txtUsername.setText(dataBean.getNickname() + "");
        } else {
            txtUsername.setText(dataBean.getUsername());
        }
        txtUsername2.setText("用户名：" + dataBean.getUsername());
        if (dataBean.getIcon() != null) {
//            imgLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757331605&di=460eb0fb70b979e44a9333b639f23bd0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F13%2F20160213103518_Ktc8J.jpeg"));
            imgLogo.setImageURI(Uri.parse(Https2http.replace(dataBean.getIcon())));
        } else {
            imgLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757377646&di=3401cc95e2f9813767c9907f961a2224&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171231%2F5ccc64e16cf84a17adfc9d0801a0775b.jpeg"));
        }
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
        // 自定义一个dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_drop_login, null);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        alertDialog = builder.setView(view)
                .setCancelable(true)
                .create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfo();
    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_account;
    }

    @OnClick({R.id.txt_back, R.id.rl_user, R.id.rl_address, R.id.txt_back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.rl_user:
                startActivity(new Intent(this, PInfoActivity.class));
                break;
            case R.id.rl_address:
                // 跳转到地址管理页面
                startActivity(new Intent(this, AddressManageActivity.class));
                break;
            case R.id.txt_back_login:
                // 退出登录
                alertDialog.show();
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                        sp.edit().putBoolean("isLogin", false).apply();
                        finish();
                        alertDialog.dismiss();
                    }
                });
                break;
        }
    }

}
