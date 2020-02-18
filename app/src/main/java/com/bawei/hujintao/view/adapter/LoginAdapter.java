package com.bawei.hujintao.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.model.bean.LoginBean;
import com.bawei.hujintao.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:06
 */
public class LoginAdapter extends BaseAdapter {
    private List<LoginBean.ResultBean> list;

    public LoginAdapter(List<LoginBean.ResultBean> result) {

        list = result;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            //加载布局
            convertView = View.inflate(parent.getContext(), R.layout.loitem, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        LoginBean.ResultBean resultBean = list.get(position);
        viewHolder.loitemName.setText(resultBean.getCommodityName());
        viewHolder.loitemPrice.setText(""+resultBean.getPrice());
        viewHolder.loitemSum.setText("已售:"+resultBean.getSaleNum());
        NetUtil.getInstance().getPhoto(resultBean.getMasterPic(),viewHolder.loitemImg);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.loitem_img)
        ImageView loitemImg;
        @BindView(R.id.loitem_name)
        TextView loitemName;
        @BindView(R.id.loitem_price)
        TextView loitemPrice;
        @BindView(R.id.loitem_sum)
        TextView loitemSum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
