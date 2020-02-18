package com.bawei.hujintao.contract;

import com.bawei.hujintao.model.bean.OrderBean;
import com.bawei.hujintao.model.bean.PayBean;

/**
 * 功能:  订单契约
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:27
 */
public interface IOrderContract {
    interface IView{
        void onSuccess(OrderBean orderBean);
        void onFailure(Throwable throwable);
        void onOSuccess(PayBean payBean);
        void onOFailure(Throwable throwable);
    }
    interface IPresenter{
        void getOrderData(int status,int page);
        void getOrderPData(String orid);
    }
    interface IModel{
        void getOrderData(int status,int page,IModelCall iModelCall);
        interface IModelCall{
            void onSuccess(OrderBean orderBean);
            void onFailure(Throwable throwable);
        }
        void getOrderPData(String orid,IModelCa iModelCa);
        interface IModelCa{
            void onOSuccess(PayBean payBean);
            void onOFailure(Throwable throwable);
        }
    }
}
