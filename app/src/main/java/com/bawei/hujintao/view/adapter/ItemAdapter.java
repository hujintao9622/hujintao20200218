package com.bawei.hujintao.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.model.bean.OrderBean;
import com.bawei.hujintao.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 11:11
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private List<OrderBean.OrderListBean.DetailListBean> list;

    public ItemAdapter(List<OrderBean.OrderListBean.DetailListBean> detailList) {

        list = detailList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定数据
        OrderBean.OrderListBean.DetailListBean detailListBean = list.get(position);
        holder.it2Name.setText(detailListBean.getCommodityName());
        holder.it2Price.setText("￥"+detailListBean.getCommodityPrice());
        String[] split = detailListBean.getCommodityPic().split(",");
        NetUtil.getInstance().getPhoto(split[0],holder.it2Img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.it2_img)
        ImageView it2Img;
        @BindView(R.id.it2_name)
        TextView it2Name;
        @BindView(R.id.it2_price)
        TextView it2Price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
