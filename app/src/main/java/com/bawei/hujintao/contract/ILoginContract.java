package com.bawei.hujintao.contract;

import com.bawei.hujintao.model.bean.LoginBean;

/**
 * 功能:  首页契约
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:54
 */
public interface ILoginContract {
    interface IView{
        void onSuccess(LoginBean loginBean);
        void onFailure(Throwable throwable);
    }
    interface IPresenter{
        void getLoginData(String key);
    }
    interface IModel{
        void getLoginData(String key,IModelCallback iModelCallback);
        interface IModelCallback{
            void onSuccess(LoginBean loginBean);
            void onFailure(Throwable throwable);
        }
    }
}
