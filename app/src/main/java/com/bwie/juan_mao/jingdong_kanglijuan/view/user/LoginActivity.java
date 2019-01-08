package com.bwie.juan_mao.jingdong_kanglijuan.view.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    @BindView(R.id.sdv_logo)
    SimpleDraweeView sdvLogo;

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
     * 在这里是登录
     *
     * @param data
     */
    @Override
    public void getUserInfo(UserInfoBean data) {
        Toast.makeText(this, data.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setEnabled(false);
//        sdvLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757377646&di=3401cc95e2f9813767c9907f961a2224&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171231%2F5ccc64e16cf84a17adfc9d0801a0775b.jpeg"));
    }

    /**
     * 用不着
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

    @Override
    public void check(boolean isChecked, String msg) {
        pd.dismiss();
        if (isChecked) {
            presenter.login();
        } else {
            btnLogin.setBackgroundResource(R.drawable.bg_login_false);
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
        return etPassword.getText().toString();
    }

    @Override
    public void onMineFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }

    @Override
    protected void initData() {
        pd = new ProgressDialog(this);
        pd.setMessage("正在登陆，请稍后...");
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
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.drawable.bg_login_false);
                } else {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.bg_login_true);
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
        return R.layout.activity_login;
    }

    @OnClick({R.id.txt_back, R.id.btn_login, R.id.txt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.btn_login:
                pd.show();
                presenter.check();
                break;
            case R.id.txt_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    /*private ImageView txtBack;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private UserPresenter presenter;
    private ProgressDialog pd;
    private TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        setContentView(R.layout.activity_login);
        initView();

        initData();
    }

    private void initData() {
        presenter = new UserPresenter();
        presenter.attach(this);

        pd = new ProgressDialog(this);
        pd.setMessage("正在登陆，请稍后...");

        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (etUsername.getText().toString().trim().length() == 0) {
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.drawable.bg_login_false);
                } else {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.bg_login_true);
                }
            }
        });
    }

    private void initView() {
        txtBack = findViewById(R.id.txt_back);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);

        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
        txtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                presenter.check();
                break;
            case R.id.txt_back:
                finish();
                break;
            case R.id.txt_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }


    @Override
    public void success(LoginBean loginBean) {
        if (loginBean != null) {
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failed(Exception e) {

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
        return null;
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
            presenter.login("http://www.zhaoapi.cn/user/login");
        } else {
            btnLogin.setBackgroundResource(R.drawable.bg_login_false);
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
