package com.bwie.juan_mao.jingdong_kanglijuan.view.user;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.sdv_logo)
    SimpleDraweeView sdvLogo;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password2)
    EditText etPassword2;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private ProgressDialog pd;

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

    @Override
    public void register(RegisterBean data) {
        pd.dismiss();
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

    @Override
    protected void onResume() {
        super.onResume();
        btnRegister.setEnabled(false);
//        sdvLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757331605&di=460eb0fb70b979e44a9333b639f23bd0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F13%2F20160213103518_Ktc8J.jpeg"));
    }

    @Override
    public void check(boolean isChecked, String msg) {
        if (isChecked) {
            pd.show();
            presenter.register();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getPassword2() {
        return etPassword2.getText().toString();
    }

    @Override
    public void onMineFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }

    @Override
    protected void initData() {
        pd = new ProgressDialog(this);
        pd.setMessage("注册中，请稍后...");
    }

    @Override
    protected void setListener() {
        super.setListener();
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etUsername.getText().toString().trim().length() == 0) {
                    btnRegister.setEnabled(false);
                    btnRegister.setBackgroundResource(R.drawable.bg_login_false);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setBackgroundResource(R.drawable.bg_login_true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.txt_back, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.btn_register:
                presenter.checkRegister();
                break;
        }
    }

    /*private ImageView txtBack;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btnRegister;
    private UserPresenter presenter;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        setContentView(R.layout.activity_register);
        initView();

        initData();
    }

    private void initData() {
        presenter = new UserPresenter();
        presenter.attach(this);

        pd = new ProgressDialog(this);
        pd.setMessage("注册中，请稍后...");

        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (etUsername.getText().toString().trim().length() == 0) {
                    btnRegister.setEnabled(false);
                    btnRegister.setBackgroundResource(R.drawable.bg_login_false);
                } else {
                    btnRegister.setEnabled(true);
                    btnRegister.setBackgroundResource(R.drawable.bg_login_true);
                }
            }
        });
    }

    private void initView() {
        txtBack = findViewById(R.id.txt_back);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPassword2 = findViewById(R.id.et_password2);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
        txtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                presenter.checkRegister();
                break;
            case R.id.txt_back:
                finish();
                break;
        }
    }

    @Override
    public void success(RegisterBean registerBean) {
        Toast.makeText(this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "网络不在状态~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getPassword2() {
        return etPassword2.getText().toString();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startActivity() {
        finish();
    }

    @Override
    public void check(boolean isChecked, String msg) {
        if (isChecked) {
            presenter.register("http://www.zhaoapi.cn/user/reg");
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void show() {
        pd.show();
    }

    @Override
    public void dismiss() {
        pd.dismiss();
    }*/
}
