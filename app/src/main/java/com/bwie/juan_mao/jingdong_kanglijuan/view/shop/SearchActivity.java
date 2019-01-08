package com.bwie.juan_mao.jingdong_kanglijuan.view.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.HistorySearchAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.HotSearchAdapter;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 三叶草 新品、红枫、小南瓜、老年机、盔甲、鼻烟壶、切肉刀 199-100、海蟹、古驰手表、加湿器、欧式果盘、多功能沙发床
 */
public class SearchActivity extends BaseActivity<ClassifyPresenter> implements IClassifyView {

    @BindView(R.id.sv_search)
    SearchView svSearch;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;
    @BindView(R.id.txt_history_search)
    TextView txtHistorySearch;
    @BindView(R.id.rv_history_search)
    RecyclerView rvHistorySearch;
    @BindView(R.id.ll_delete_history)
    LinearLayout llDeleteHistory;
    String[] hotSearches = {"三叶草 新品", "红枫", "小南瓜", "老年机", "盔甲", "鼻烟壶", "切肉刀 199-100", "海蟹", "古驰手表", "加湿器", "欧式果盘", "多功能沙发床"};
    private List<String> hotSearchList;
    private HotSearchAdapter hotSearchAdapter;
    private HistorySearchBeanDao dao;
    private List<HistorySearchBean> list;
    private HistorySearchAdapter historySearchAdapter;
    private AlertDialog alertDialog;
    private Button btnCancel;
    private Button btnConfirm;

    @Override
    protected void initData() {
        // 自定义一个dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_delete_history, null);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        alertDialog = builder.setView(view)
                .setCancelable(true)
                .create();

        // 搜索提交监听
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 点击提交跳转到商品列表页面，带着商品名，并且存到数据库
                /*Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();*/
                insertData(query);
                Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
                intent.putExtra("type", "search");
                intent.putExtra("content", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

    private void updateData() {
        // 查询数据库
        list = dao.queryBuilder()
                .build()
                .list();
        if (list.size() != 0) {
            txtHistorySearch.setVisibility(View.VISIBLE);
            rvHistorySearch.setVisibility(View.VISIBLE);
            llDeleteHistory.setVisibility(View.VISIBLE);
        } else {
            txtHistorySearch.setVisibility(View.GONE);
            rvHistorySearch.setVisibility(View.GONE);
            llDeleteHistory.setVisibility(View.GONE);
        }
        // 配置历史搜索
        historySearchAdapter = new HistorySearchAdapter(this, list);
        historySearchAdapter.setOnHistoryItemClickListener(new HistorySearchAdapter.OnHistoryItemClickListener() {
            @Override
            public void onHistoryClick(View view, int position) {
                String name = list.get(position).getSearchName();
                insertData(name);
                Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
                intent.putExtra("type", "search");
                intent.putExtra("content", name);
                startActivity(intent);
            }
        });
        rvHistorySearch.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvHistorySearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        rvHistorySearch.setAdapter(historySearchAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获取dao查询数据库
        DaoSession daoSession = DaoManager.getInstance(this).getDaoSession();
        dao = daoSession.getHistorySearchBeanDao();

        // 配置热搜
        hotSearchList = new ArrayList<>();
        for (String hotSearch : hotSearches) {
            hotSearchList.add(hotSearch);
        }
        hotSearchAdapter = new HotSearchAdapter(this, hotSearchList);
        hotSearchAdapter.setOnHotItemClickListener(new HotSearchAdapter.OnHotItemClickListener() {
            @Override
            public void onHotClick(View view, int position) {
                String name = hotSearchList.get(position);
                insertData(name);
                Intent intent = new Intent(SearchActivity.this, ShopListActivity.class);
                intent.putExtra("type", "search");
                intent.putExtra("content", name);
                startActivity(intent);
            }
        });
        rvHotSearch.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        rvHotSearch.setAdapter(hotSearchAdapter);


        updateData();
    }

    @Override
    protected ClassifyPresenter providePresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search;
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

    }

    /**
     * 用不到
     * @param data
     */
    @Override
    public void addCart(RegisterBean data) {

    }

    @Override
    public void getProductDetail(ProductDetailsBean data) {

    }

    @Override
    public void onClassifyFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.txt_cancel, R.id.ll_delete_history, R.id.sv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_cancel:
                finish();
                break;
            case R.id.ll_delete_history:
                // 点击弹框确认是否清空
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
                        dao.deleteAll();
                        updateData();
                        alertDialog.dismiss();
                    }
                });
                break;
            case R.id.sv_search:
                svSearch.onActionViewExpanded();
                break;
        }
    }
}