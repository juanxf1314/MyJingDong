package com.bwie.juan_mao.jingdong_kanglijuan.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseFragment;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.NewsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.FindPresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IFindView;
import com.bwie.juan_mao.jingdong_kanglijuan.view.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 卷猫~ on 2018/10/12.
 */

public class FindFragment extends BaseFragment<FindPresenter> implements IFindView {
    @BindView(R.id.tbl_channel)
    TabLayout tblChannel;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    String[] titles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    String[] arguments = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    public void getNews(NewsBean data) {

    }

    @Override
    public void onFindFailed(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        titleList = new ArrayList<>();
        for (String title : titles) {
            titleList.add(title);
        }
        fragmentList = new ArrayList<>();
        for (String argument : arguments) {
            fragmentList.add(NewsFragment.getInstance(argument));
        }

        vpContent.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        tblChannel.setTabMode(TabLayout.MODE_SCROLLABLE);
        tblChannel.setupWithViewPager(vpContent);
    }

    @Override
    protected FindPresenter providePresenter() {
        return new FindPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_find;
    }
}
