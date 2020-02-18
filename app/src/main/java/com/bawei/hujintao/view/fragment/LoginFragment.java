package com.bawei.hujintao.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.bawei.hujintao.R;
import com.bawei.hujintao.base.BaseFragment;
import com.bawei.hujintao.contract.ILoginContract;
import com.bawei.hujintao.database.DaoMaster;
import com.bawei.hujintao.database.DaoSession;
import com.bawei.hujintao.model.bean.LoginBean;
import com.bawei.hujintao.presenter.LoginPresenter;
import com.bawei.hujintao.util.NetUtil;
import com.bawei.hujintao.view.adapter.LoginAdapter;

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

    @Override
    protected void initView(View view) {
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
        if (NetUtil.getInstance().hasNet(getContext())){
            presenter.getLoginData("板鞋");
        }else{
            Toast.makeText(getContext(), "没有网了", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Log.i("xx",loginBean.getResult().size()+"");
        //成功展示
        List<LoginBean.ResultBean> result = loginBean.getResult();
        loRc.setAdapter(new LoginAdapter(result));
    }

    @Override
    public void onFailure(Throwable throwable) {
        //错误信息
        Log.e("tag",throwable.getMessage());
    }

    @OnClick(R.id.lo_bt)
    public void onViewClicked() {
        //点击搜索
        String string = loEd.getText().toString();
        if (TextUtils.isEmpty(string)){
            Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.getLoginData(string);
    }
}
