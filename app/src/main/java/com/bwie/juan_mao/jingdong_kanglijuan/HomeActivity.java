package com.bwie.juan_mao.jingdong_kanglijuan;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BasePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.fragment.CartFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.fragment.ClassifyFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.fragment.FindFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.fragment.HomeFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.fragment.MineFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.txt_home)
    ImageView txtHome;
    @BindView(R.id.txt_classify)
    ImageView txtClassify;
    @BindView(R.id.txt_find)
    ImageView txtFind;
    @BindView(R.id.txt_shop)
    ImageView txtShop;
    @BindView(R.id.txt_mine)
    ImageView txtMine;
    private HomeFragment homeFrag;
    private ClassifyFragment classifyFrag;
    private CartFragment cartFrag;
    private MineFragment mineFrag;
    private FindFragment findFrag;
    private FragmentManager manager;

    @Override
    protected void initData() {
        homeFrag = new HomeFragment();
        classifyFrag = new ClassifyFragment();
        cartFrag = new CartFragment();
        mineFrag = new MineFragment();
        findFrag = new FindFragment();

        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.content, homeFrag)
                .commit();
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick({R.id.txt_home, R.id.txt_classify, R.id.txt_find, R.id.txt_shop, R.id.txt_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_home:
                manager.beginTransaction()
                        .replace(R.id.content, homeFrag)
                        .commit();
                changeBackground(0);
                break;
            case R.id.txt_classify:
                manager.beginTransaction()
                        .replace(R.id.content, classifyFrag)
                        .commit();
                changeBackground(1);
                break;
            case R.id.txt_find:
                manager.beginTransaction()
                        .replace(R.id.content, findFrag)
                        .commit();
                changeBackground(2);
                break;
            case R.id.txt_shop:
                manager.beginTransaction()
                        .replace(R.id.content, cartFrag)
                        .commit();
                changeBackground(3);
                break;
            case R.id.txt_mine:
                manager.beginTransaction()
                        .replace(R.id.content, mineFrag)
                        .commit();
                changeBackground(4);
                break;
        }
    }

    private void changeBackground(int index) {
        txtHome.setImageResource(index == 0 ? R.drawable.ac1 : R.drawable.ac0);
        txtClassify.setImageResource(index == 1 ? R.drawable.abx : R.drawable.abw);
        txtFind.setImageResource(index == 2 ? R.drawable.abz : R.drawable.aby);
        txtShop.setImageResource(index == 3 ? R.drawable.abv : R.drawable.abu);
        txtMine.setImageResource(index == 4 ? R.drawable.ac3 : R.drawable.ac2);
    }
}
