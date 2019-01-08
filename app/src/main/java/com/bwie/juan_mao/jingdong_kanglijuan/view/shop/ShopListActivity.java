package com.bwie.juan_mao.jingdong_kanglijuan.view.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.ShopListAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DaoSession;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.HistorySearchBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.HistorySearchBeanDao;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.ClassifyPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.DaoManager;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IClassifyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopListActivity extends BaseActivity<ClassifyPresenter> implements IClassifyView {

    @BindView(R.id.sv_search)
    SearchView svSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.txt_tip)
    TextView txtTip;
    @BindView(R.id.rv_shop_list)
    XRecyclerView rvShopList;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_cut)
    ImageView imgCut;
    private boolean isFlow = false;
    private List<ShopListBean.DataBean> list;
    private ShopListAdapter adapter;
    private int page = 1;
    private String type;
    private boolean isLoadMore = false;
    private int pscid;
    private Handler handler = new Handler();
    private HistorySearchBeanDao dao;

    @Override
    protected void initData() {
        // 初始化dao
        DaoSession daoSession = DaoManager.getInstance(this).getDaoSession();
        dao = daoSession.getHistorySearchBeanDao();

        rvShopList.setPullRefreshEnabled(true);
        rvShopList.setPullRefreshEnabled(true);
        rvShopList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isLoadMore = false;
                if (type.equals("search")) {

                } else if (type.equals("sort")) {
                    presenter.getProducts(pscid, page);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvShopList.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                page++;
                isLoadMore = true;
                if (type.equals("search")) {

                } else if (type.equals("sort")) {
                    presenter.getProducts(pscid, page);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvShopList.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        list = new ArrayList<>();
        adapter = new ShopListAdapter(this, list);
        if (isFlow) {
            rvShopList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else {
            rvShopList.setLayoutManager(new LinearLayoutManager(this));
        }
        adapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShopListBean.DataBean dataBean = list.get(position);
                Intent intent = new Intent(ShopListActivity.this, ShopWebActivity.class);
                intent.putExtra("pid", dataBean.getPid());
                startActivity(intent);
            }
        });
        rvShopList.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                page = 1;
                presenter.searchProducts(query, page);
                insertData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("search")) {
            String content = intent.getStringExtra("content");
            presenter.searchProducts(content, page);
            svSearch.setQueryHint(content);
        } else if (type.equals("sort")) {
            // 获取pscid
            pscid = intent.getIntExtra("pscid", 0);
            presenter.getProducts(pscid, page);
        }

    }

    private void insertData(String query) {
        List<HistorySearchBean> list = dao.queryBuilder()
                .where(HistorySearchBeanDao.Properties.SearchName.eq(query))
                .build()
                .list();
        if (list.size() == 0) {
            dao.insert(new HistorySearchBean(null, query));
        } else {
            for (HistorySearchBean historySearchBean : list) {
                dao.delete(historySearchBean);
            }
            dao.insert(new HistorySearchBean(null, query));
        }
    }

    @Override
    protected ClassifyPresenter providePresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_shop_list;
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
    public void getCategory(CategoryBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getProductCategory(ProductCategoryBean data) {

    }

    @Override
    public void getShopList(ShopListBean data) {
        List<ShopListBean.DataBean> shopBean = data.getData();
        if (shopBean.size() == 0) {
            rvShopList.setVisibility(View.GONE);
            txtTip.setVisibility(View.VISIBLE);
        } else {
            rvShopList.setVisibility(View.VISIBLE);
            txtTip.setVisibility(View.GONE);
            // 添加数据
            if (!isLoadMore) {
                list.clear();
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void addCart(RegisterBean data) {

    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getProductDetail(ProductDetailsBean data) {

    }

    @Override
    public void onClassifyFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        page--;
    }

    @OnClick({R.id.img_back, R.id.img_cut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_cut:
                isFlow = !isFlow;
                if (isFlow) {
                    imgCut.setImageResource(R.drawable.kind_liner);
                } else {
                    imgCut.setImageResource(R.drawable.kind_grid);
                }
                adapter.setFlow(isFlow);
                if (isFlow) {
                    rvShopList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                } else {
                    rvShopList.setLayoutManager(new LinearLayoutManager(this));
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
