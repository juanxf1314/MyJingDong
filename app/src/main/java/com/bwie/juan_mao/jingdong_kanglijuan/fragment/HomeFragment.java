package com.bwie.juan_mao.jingdong_kanglijuan.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.CategoryAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.RecommendShopAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.BannerAndSecKillBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.MessageBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopperBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.HomePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IHomeView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.SearchActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.ShopWebActivity;
import com.recker.flybanner.FlyBanner;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 卷猫~ on 2018/10/12.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {

    @BindView(R.id.rv_center)
    RecyclerView rvCenter;
    @BindView(R.id.rv_bottom)
    RecyclerView rvBottom;
    @BindView(R.id.img_sweep)
    ImageView imgSweep;
    @BindView(R.id.sv_search)
    SearchView svSearch;
    @BindView(R.id.img_msg)
    ImageView imgMsg;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.sv_layout)
    ScrollView svLayout;
    @BindView(R.id.fb_banner)
    FlyBanner fbBanner;
    Unbinder unbinder;
    private List<String> bannerPaths;
    private List<CategoryBean.DataBean> categorylist;
    private CategoryAdapter categoryAdapter;
    private List<RecommendBean.DataBean.ListBean> list;
    private RecommendShopAdapter recommendShopAdapter;

    @Override
    protected void initData() {
        presenter.getBannerAndSecKill();
        presenter.getCategory();
        presenter.getRecommend(71);

        setCategory();
        setRecommend();
    }

    private void setRecommend() {
        list = new ArrayList<>();
        rvBottom.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recommendShopAdapter = new RecommendShopAdapter(getActivity(), list);
        rvBottom.setAdapter(recommendShopAdapter);
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

    private void setCategory() {
        // 设置嵌套滑动冲突
        rvBottom.setNestedScrollingEnabled(false);

        categorylist = new ArrayList<>();
        rvCenter.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(getActivity(), categorylist);
        rvCenter.setAdapter(categoryAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setListener() {
        super.setListener();
        svLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i3 >= 80) {
                    llSearch.setBackgroundColor(Color.WHITE);
                    svSearch.setBackgroundResource(R.drawable.bg_edit_search_gray);
                    imgSweep.setImageResource(R.drawable.sao_hei);
                    imgMsg.setImageResource(R.drawable.msg_hei);
                    imgSweep.setBackground(null);
                    imgMsg.setBackground(null);
                } else {
                    llSearch.setBackground(null);
                    svSearch.setBackgroundResource(R.drawable.bg_edit_search);
                    imgSweep.setImageResource(R.drawable.a_s);
                    imgMsg.setImageResource(R.drawable.a9x);
                    imgSweep.setBackgroundColor(Color.argb(103, 107, 124, 128));
                    imgMsg.setBackgroundColor(Color.argb(103, 107, 124, 128));
                }
            }
        });
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void getBannerAndSecKill(BannerAndSecKillBean data) {
        bannerPaths = new ArrayList<>();
        List<String> info = new ArrayList<>();
        for (BannerAndSecKillBean.DataBean dataBean : data.getData()) {
            bannerPaths.add(Https2http.replace(dataBean.getIcon()));
            info.add(dataBean.getTitle());
        }
        fbBanner.setImagesUrl(bannerPaths);
        marqueeView.startWithList(info);
    }

    @Override
    public void getCategory(CategoryBean data) {
        categorylist.clear();
        categorylist.addAll(data.getData());
        categoryAdapter.notifyDataSetChanged();
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
     * 购物车，用不到
     *
     * @param data
     */
    @Override
    public void getCarts(MessageBean<List<ShopperBean<List<ProductBean>>>> data) {

    }

    @Override
    public void onHomeFailed(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_sweep, R.id.sv_search, R.id.img_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_sweep:
                // 扫一扫
                break;
            case R.id.sv_search:
                // 搜索
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.img_msg:
                Toast.makeText(getActivity(), "此功能尚在开发中~", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
