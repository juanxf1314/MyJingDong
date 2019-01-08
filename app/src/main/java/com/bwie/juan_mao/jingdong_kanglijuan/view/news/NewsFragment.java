package com.bwie.juan_mao.jingdong_kanglijuan.view.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.adapter.NewsAdapter;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.FindPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IFindView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 卷猫~ on 2018/11/13.
 */

public class NewsFragment extends BaseFragment<FindPresenter> implements IFindView {

    public static final String URL = "http://www.xieast.com/api/news/news.php";
    public static final String FLAG = "argument";
    @BindView(R.id.rv_news)
    XRecyclerView rvNews;
    private List<NewsBean.DataBean> list;
    private NewsAdapter newsAdapter;
    private Handler handler = new Handler();
    private int page = 1;
    private String type;
    private boolean isLoadMore = false;

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        type = bundle.getString(FLAG);
        presenter.getNews(URL, type, 1);

        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(getActivity(), list);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvNews.setAdapter(newsAdapter);

        rvNews.setLoadingMoreEnabled(true);
        rvNews.setPullRefreshEnabled(true);
        rvNews.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isLoadMore = false;
                presenter.getNews(URL, type, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvNews.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                page++;
                isLoadMore = true;
                presenter.getNews(URL, type, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvNews.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    public static NewsFragment getInstance(String type) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FLAG, type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    protected FindPresenter providePresenter() {
        return new FindPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void getNews(NewsBean data) {
        if (!isLoadMore) {
            list.clear();
        }
        list.addAll(data.getData());
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFindFailed(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        page--;
    }

}
