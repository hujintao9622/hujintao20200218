package com.bawei.hujintao.view.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseFragment;
import com.bawei.hujintao.base.BasePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能:  订单页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:40
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.or_tb)
    TabLayout orTb;
    @BindView(R.id.or_vp)
    ViewPager orVp;
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> stringList=new ArrayList<>();
    @Override
    protected void initView(View view) {

    }

    @Override
    protected BasePresenter prvidePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.order;
    }

    @Override
    protected void initData() {
        //添加数据
        OrderItemFragment o1 = OrderItemFragment.getInstance(0);
        OrderItemFragment o2 = OrderItemFragment.getInstance(1);
        OrderItemFragment o3 = OrderItemFragment.getInstance(2);
        OrderItemFragment o4 = OrderItemFragment.getInstance(3);
        OrderItemFragment o5 = OrderItemFragment.getInstance(9);
        fragmentList.add(o1);
        fragmentList.add(o2);
        fragmentList.add(o3);
        fragmentList.add(o4);
        fragmentList.add(o5);
        stringList.add("全部订单");
        stringList.add("待支付");
        stringList.add("待收货");
        stringList.add("待评价");
        stringList.add("已完成");
        orVp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stringList.get(position);
            }
        });
        //关联
        orTb.setupWithViewPager(orVp);
    }
}
