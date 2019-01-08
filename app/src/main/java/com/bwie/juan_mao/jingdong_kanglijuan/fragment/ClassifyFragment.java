package com.bwie.juan_mao.jingdong_kanglijuan.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.ChildCategoryAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.MCategoryAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.ClassifyPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IClassifyView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.SearchActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.view.shop.ShopListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 卷猫~ on 2018/10/12.
 */

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements IClassifyView {
    @BindView(R.id.img_sweep)
    ImageView imgSweep;
    @BindView(R.id.sv_search)
    SearchView svSearch;
    @BindView(R.id.img_msg)
    ImageView imgMsg;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_child_category)
    RecyclerView rvChildCategory;
    private List<CategoryBean.DataBean> categories;
    private MCategoryAdapter mCategoryAdapter;
    private List<ProductCategoryBean.DataBean> dataBeans;
    private ChildCategoryAdapter childCategoryAdapter;
    private View viewP;
    private TextView txtP;

    @Override
    public void getCategory(CategoryBean data) {
        categories.clear();
        categories.addAll(data.getData());
        mCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getProductCategory(ProductCategoryBean data) {
        dataBeans.clear();
        dataBeans.addAll(data.getData());
        childCategoryAdapter.notifyDataSetChanged();
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getShopList(ShopListBean data) {

    }

    /**
     * 用不到
     *
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
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        categories = new ArrayList<>();
        mCategoryAdapter = new MCategoryAdapter(getActivity(), categories);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCategoryAdapter.setOnItemClickListener(new MCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView txt, int position) {
                if (viewP != null) {
                    viewP.setBackgroundColor(Color.rgb(244, 244, 244));
                }
                if (txtP != null) {
                    txtP.setTextColor(Color.rgb(145, 145, 145));
                }
                viewP = view;
                txtP = txt;
                view.setBackgroundColor(Color.WHITE);
                txt.setTextColor(Color.BLACK);
                int cid = categories.get(position).getCid();
                presenter.getProductCategory(cid);
            }
        });
        rvCategory.setAdapter(mCategoryAdapter);
        presenter.getCategory();
        presenter.getProductCategory(1);

        dataBeans = new ArrayList<>();
        childCategoryAdapter = new ChildCategoryAdapter(getActivity(), dataBeans);
        childCategoryAdapter.setOnChildClickListener(new ChildCategoryAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(View view, int position, ProductCategoryBean.DataBean bean) {
                ProductCategoryBean.DataBean.ListBean listBean = bean.getList().get(position);
                int pscid = listBean.getPscid();
                Intent intent = new Intent(getActivity(), ShopListActivity.class);
                intent.putExtra("type", "sort");
                intent.putExtra("pscid", pscid);
                startActivity(intent);
            }
        });
        rvChildCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvChildCategory.setAdapter(childCategoryAdapter);
    }

    @Override
    protected ClassifyPresenter providePresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_classify;
    }

    @OnClick({R.id.img_sweep, R.id.sv_search, R.id.img_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_sweep:
                // 扫一扫
                break;
            case R.id.sv_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.img_msg:
                Toast.makeText(getActivity(), "此功能尚在开发中~", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
