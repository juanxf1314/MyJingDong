package com.bwie.juan_mao.jingdong_kanglijuan.view.user;

import android.content.Context;
import android.content.Intent;
import android.view.View;
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

import butterknife.BindView;
import butterknife.OnClick;

public class CNicknameActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.txt_save)
    TextView txtSave;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.img_delete)
    ImageView imgDelete;

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
     * 在这里是修改昵称的接口回调
     *
     * @param data
     */
    @Override
    public void register(RegisterBean data) {
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
        return R.layout.activity_cnickname;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        etNickname.setText(nickname);
    }

    @OnClick({R.id.txt_back, R.id.txt_save, R.id.img_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.txt_save:
                String nickname = etNickname.getText().toString();
                presenter.updateNickname(nickname);
                break;
            case R.id.img_delete:
                etNickname.setText("");
                break;
        }
    }

    /* private ImageView txtBack;
    private TextView txtSave;
    private EditText etNickname;
    private ImageView imgDelete;
    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fitsSystemWindows(false).init();
        setContentView(R.layout.activity_cnickname);
        initView();

        initData();
    }

    private void initData() {
        presenter = new UserPresenter();
        presenter.attach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickname", "");
        etNickname.setText(nickname);
    }

    private void initView() {
        txtBack = findViewById(R.id.txt_back);
        txtSave = findViewById(R.id.txt_save);
        etNickname = findViewById(R.id.et_nickname);
        imgDelete = findViewById(R.id.img_delete);

        txtBack.setOnClickListener(this);
        txtSave.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.txt_save:
                String nickname = etNickname.getText().toString();
                presenter.setNickName("http://www.zhaoapi.cn/user/updateNickName", nickname);
                break;
            case R.id.img_delete:
                etNickname.setText("");
                break;
        }
    }

    @Override
    public void success(NicknameBean nicknameBean) {
        Toast.makeText(this, nicknameBean.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
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

    }

    @Override
    public void check(boolean isChecked, String msg) {

    }

    @Override
    public void show() {

    }

    @Override
    public void dismiss() {

    }*/
}
