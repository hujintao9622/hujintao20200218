package com.bawei.hujintao.view.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseActivity;
import com.bawei.hujintao.base.BasePresenter;
import com.bawei.hujintao.view.fragment.LoginFragment;
import com.bawei.hujintao.view.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ma_vp)
    ViewPager maVp;
    @BindView(R.id.ma_rg)
    RadioGroup maRg;
    private List<Fragment> list=new ArrayList<>();
    @Override
    protected void initData() {
        //添加数据
        LoginFragment loginFragment = new LoginFragment();
        OrderFragment orderFragment = new OrderFragment();
        list.add(loginFragment);
        list.add(orderFragment);
        //设置适配器
        maVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    protected void initView() {
        maVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                maRg.check(maRg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        maRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ma_rb1:
                        maVp.setCurrentItem(0);
                        break;
                    case R.id.ma_rb2:
                        maVp.setCurrentItem(1);
                        break;
                }
            }
        });
        //默认初始页面
        maVp.setCurrentItem(0);
        maRg.check(maRg.getChildAt(0).getId());
        maVp.setOffscreenPageLimit(2);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

}
