package com.bawei.hujintao.presenter;

import com.bawei.hujintao.base.BasePresenter;
import com.bawei.hujintao.contract.IOrderContract;
import com.bawei.hujintao.model.OrderModel;
import com.bawei.hujintao.model.bean.OrderBean;
import com.bawei.hujintao.model.bean.PayBean;

/**
 * 功能:  订单P层
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:31
 */
public class OrderPresenter extends BasePresenter<IOrderContract.IView> implements IOrderContract.IPresenter {

    private OrderModel orderModel;

    @Override
    protected void initModel() {
        orderModel = new OrderModel();
    }

    @Override
    public void getOrderData(int status,int page) {
        orderModel.getOrderData(status,page, new IOrderContract.IModel.IModelCall() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                view.onSuccess(orderBean);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }

    @Override
    public void getOrderPData(String orid) {
        orderModel.getOrderPData(orid, new IOrderContract.IModel.IModelCa() {
            @Override
            public void onOSuccess(PayBean payBean) {
                view.onOSuccess(payBean);
            }

            @Override
            public void onOFailure(Throwable throwable) {
                view.onOFailure(throwable);
            }
        });
    }
}
