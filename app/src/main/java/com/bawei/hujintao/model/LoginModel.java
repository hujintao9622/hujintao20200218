package com.bawei.hujintao.model;


import com.bawei.hujintao.contract.ILoginContract;
import com.bawei.hujintao.model.bean.LoginBean;
import com.bawei.hujintao.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能:  首页M层
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:49
 */
public class LoginModel implements ILoginContract.IModel {

    @Override
    public void getLoginData(int page,String key, IModelCallback iModelCallback) {
        NetUtil.getInstance().getApi().login(key,page,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        //回调数据
                        iModelCallback.onSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
