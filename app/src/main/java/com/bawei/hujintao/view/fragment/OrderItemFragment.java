package com.bawei.hujintao.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseFragment;
import com.bawei.hujintao.contract.IOrderContract;
import com.bawei.hujintao.database.DaoMaster;
import com.bawei.hujintao.database.DaoSession;
import com.bawei.hujintao.database.JsonSqliteDao;
import com.bawei.hujintao.model.bean.JsonSqlite;
import com.bawei.hujintao.model.bean.OrderBean;
import com.bawei.hujintao.model.bean.PayBean;
import com.bawei.hujintao.presenter.OrderPresenter;
import com.bawei.hujintao.util.NetUtil;
import com.bawei.hujintao.view.adapter.OrItAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:35
 */
public class OrderItemFragment extends BaseFragment<OrderPresenter> implements IOrderContract.IView {
    @BindView(R.id.orit_rc)
    RecyclerView oritRc;
    @BindView(R.id.orit_sma)
    SmartRefreshLayout oritSma;
    @BindView(R.id.orit_img)
    ImageView oritImg;
    private int page=1;
    private int status=0;
    List<OrderBean.OrderListBean> list=new ArrayList<>();
    private JsonSqliteDao jsonSqliteDao;
    private OrItAdapter orItAdapter;

    @Override
    protected void initView(View view) {
        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), "app.db");
        jsonSqliteDao = daoSession.getJsonSqliteDao();
        //允许刷新加载
        oritSma.setEnableLoadMore(true);
        oritSma.setEnableRefresh(true);
        //设置监听
        oritSma.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //清空数据
                list.clear();
                page=1;
                //请求数据
                presenter.getOrderData(status,page);
                //隐藏刷新
                oritSma.finishRefresh();
            }
        });
        oritSma.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                //请求数据
                presenter.getOrderData(status,page);
                //隐藏加载
                oritSma.finishLoadMore();
            }
        });
    }

    @Override
    protected OrderPresenter prvidePresenter() {
        return new OrderPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.orderit;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            status = bundle.getInt("status");
        }
        //判断有无网
        if (NetUtil.getInstance().hasNet(getContext())){
            presenter.getOrderData(status,page);
        }else {
            //从数据库取数据
            Query<JsonSqlite> build = jsonSqliteDao.queryBuilder().build();
            JsonSqlite unique = build.unique();
            String json = unique.getJson();
            OrderBean orderBean = new Gson().fromJson(json, OrderBean.class);
            List<OrderBean.OrderListBean> orderList = orderBean.getOrderList();
            if (orderList.size()==0){
                oritImg.setVisibility(View.VISIBLE);
                oritImg.setImageResource(R.mipmap.ic_launcher_round);
            }else {
                oritImg.setVisibility(View.GONE);
            }
            list.addAll(orderList);
            //设置适配器展示
            oritRc.setLayoutManager(new LinearLayoutManager(getContext()));
            oritRc.setAdapter(new OrItAdapter(list));

        }
    }

    @Override
    public void onSuccess(OrderBean orderBean) {
        jsonSqliteDao.deleteAll();
        //存储数据库
        String string = orderBean.toString();
        jsonSqliteDao.insert(new JsonSqlite(1,string));
        List<OrderBean.OrderListBean> orderList = orderBean.getOrderList();
        if (orderList.size()==0){
            oritImg.setVisibility(View.VISIBLE);
            oritImg.setImageResource(R.mipmap.ic_launcher_round);
        }else {
            oritImg.setVisibility(View.GONE);
        }
        list.addAll(orderList);
        oritRc.setLayoutManager(new LinearLayoutManager(getContext()));
        orItAdapter = new OrItAdapter(list);
        //点击事件
        orItAdapter.setOnClickListener(new OrItAdapter.OnClickListener() {
            @Override
            public void OnClick(String orderId) {
                presenter.getOrderPData(orderId);
            }
        });
        oritRc.setAdapter(orItAdapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("tagor",throwable.getMessage());
    }

    @Override
    public void onOSuccess(PayBean payBean) {
        if (payBean.getStatus().equals("0000")){
            if (orItAdapter != null) {
                orItAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onOFailure(Throwable throwable) {
Log.e("tagpay",throwable.getMessage());
    }

    public static OrderItemFragment getInstance(int status) {
        OrderItemFragment orderItemFragment = new OrderItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        orderItemFragment.setArguments(bundle);
        return orderItemFragment;
    }
}
