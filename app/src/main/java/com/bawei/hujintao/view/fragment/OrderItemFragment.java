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
    @BindView(R.id.orit_img)
    ImageView oritImg;
    private int page=1;
    private int status=0;
    private JsonSqliteDao jsonSqliteDao;
    private OrItAdapter orItAdapter;

    @Override
    protected void initView(View view) {
        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), "app.db");
        jsonSqliteDao = daoSession.getJsonSqliteDao();
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
            List<JsonSqlite> list1 = jsonSqliteDao.queryBuilder().list();
            for (int i = 0; i < list1.size(); i++) {
                JsonSqlite jsonSqlite = list1.get(i);
                String json = jsonSqlite.getJson();
                OrderBean orderBean = new Gson().fromJson(json, OrderBean.class);
                List<OrderBean.OrderListBean> orderList = orderBean.getOrderList();
                //设置适配器展示
                oritRc.setLayoutManager(new LinearLayoutManager(getContext()));
                oritRc.setAdapter(new OrItAdapter(orderList));
            }

        }
    }

    @Override
    public void onSuccess(OrderBean orderBean) {
        //存储数据库
        String string = new Gson().toJson(orderBean);
        jsonSqliteDao.insert(new JsonSqlite(null,string));
        List<OrderBean.OrderListBean> orderList = orderBean.getOrderList();
        if (orderList.size()==0){
            oritImg.setVisibility(View.VISIBLE);
            oritImg.setImageResource(R.mipmap.ic_launcher_round);
        }else {
            oritImg.setVisibility(View.GONE);
        }
        oritRc.setLayoutManager(new LinearLayoutManager(getContext()));
        orItAdapter = new OrItAdapter(orderList);
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
