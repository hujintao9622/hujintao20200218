package com.bawei.hujintao.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseFragment;
import com.bawei.hujintao.contract.ILoginContract;
import com.bawei.hujintao.model.bean.LoginBean;
import com.bawei.hujintao.presenter.LoginPresenter;
import com.bawei.hujintao.util.NetUtil;
import com.bawei.hujintao.view.adapter.LoginAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:39
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements ILoginContract.IView {
    @BindView(R.id.lo_ed)
    EditText loEd;
    @BindView(R.id.lo_bt)
    Button loBt;
    @BindView(R.id.lo_rc)
    GridView loRc;
    @BindView(R.id.lo_sm)
    SmartRefreshLayout loSm;
    private int page=1;
    private String key="板鞋";
    List<LoginBean.ResultBean> list=new ArrayList<>();
    @Override
    protected void initView(View view) {
        loSm.setEnableRefresh(true);
        loSm.setEnableLoadMore(true);
        loSm.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                page=1;
                presenter.getLoginData(page,key);
                loSm.finishRefresh();
            }
        });
        loSm.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getLoginData(page,key);
                loSm.finishLoadMore();
            }
        });
    }

    @Override
    protected LoginPresenter prvidePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.login;
    }

    @Override
    protected void initData() {
        if (NetUtil.getInstance().hasNet(getContext())) {
            presenter.getLoginData(page,"板鞋");
        } else {
            Toast.makeText(getContext(), "没有网了", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Log.i("xx", loginBean.getResult().size() + "");
        //成功展示
        List<LoginBean.ResultBean> result = loginBean.getResult();
        list.addAll(result);
        loRc.setAdapter(new LoginAdapter(list));
    }

    @Override
    public void onFailure(Throwable throwable) {
        //错误信息
        Log.e("tag", throwable.getMessage());
    }

    @OnClick(R.id.lo_bt)
    public void onViewClicked() {
        //点击搜索
       key = loEd.getText().toString();
        if (TextUtils.isEmpty(key)) {
            Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.getLoginData(page,key);
    }
}
