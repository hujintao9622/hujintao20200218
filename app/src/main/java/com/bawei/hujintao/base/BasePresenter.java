package com.bawei.hujintao.base;

/**
 * 功能:  presenter基类
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:25
 */
public abstract class BasePresenter<V> {
    protected V view;

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
