package com.bwie.juan_mao.jingdong_kanglijuan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.RecommendShopAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.MinePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.ShopWebActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.view.user.AccountActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.view.user.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 卷猫~ on 2018/10/12.
 */

public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {

    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.sdv_user_icon)
    SimpleDraweeView sdvUserIcon;
    private List<RecommendBean.DataBean.ListBean> list;
    private RecommendShopAdapter recommendShopAdapter;

    @Override
    protected void initData() {
        presenter.getRecommend(71);

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
    }

    @Override
    public void onResume() {
        super.onResume();
        preLogin();
    }

    private void preLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        if (!isLogin) {
            txtUsername.setText("登录/注册");
            sdvUserIcon.setImageResource(R.drawable.user);
            /*Toast.makeText(getActivity(), "请先进行登录", Toast.LENGTH_SHORT).show();*/
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
            ll.setBackgroundResource(R.drawable.b5a);
        } else {
            presenter.getUserInfo();
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), AccountActivity.class));
                }
            });
            ll.setBackgroundResource(R.drawable.reg_bg);
        }
    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void getUserInfo(UserInfoBean data) {
        UserInfoBean.DataBean dataBean = data.getData();
        if (dataBean.getNickname() != null) {
            txtUsername.setText(dataBean.getNickname() + "");
        } else {
            txtUsername.setText(dataBean.getUsername());
        }
        if (dataBean.getIcon() != null) {
//            sdvUserIcon.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757331605&di=460eb0fb70b979e44a9333b639f23bd0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F13%2F20160213103518_Ktc8J.jpeg"));
            sdvUserIcon.setImageURI(Https2http.replace(dataBean.getIcon()));
        } else {
            sdvUserIcon.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757377646&di=3401cc95e2f9813767c9907f961a2224&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171231%2F5ccc64e16cf84a17adfc9d0801a0775b.jpeg"));
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
    public void updateAddress(UpdateAddressBean data) {

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
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
