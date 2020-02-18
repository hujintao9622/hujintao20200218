package com.bawei.hujintao.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.model.bean.OrderBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  订单适配器
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 10:57
 */
public class OrItAdapter extends RecyclerView.Adapter<OrItAdapter.MyViewHolder> {
    private List<OrderBean.OrderListBean> list;

    public OrItAdapter(List<OrderBean.OrderListBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定数据
        OrderBean.OrderListBean orderListBean = list.get(position);
        int orderStatus = orderListBean.getOrderStatus();
        holder.it1Hao.setText(orderListBean.getOrderId());
        long orderTime = orderListBean.getOrderTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(orderTime);
        holder.it1Time.setText(format);
        if (orderStatus==1){
            holder.it1Bt.setText("去付款");
            //点击之间
            holder.it1Bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.OnClick(orderListBean.getOrderId());
                    }
                }
            });
        }else if (orderStatus==2){
            holder.it1Bt.setText("确认收货");
        }else if (orderStatus==3){
            holder.it1Bt.setText("去评价");
        }else if (orderStatus==9){
            holder.it1Bt.setVisibility(View.GONE);
        }
        holder.it1Rc.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.it1Rc.setAdapter(new ItemAdapter(orderListBean.getDetailList()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.it1_hao)
        TextView it1Hao;
        @BindView(R.id.it1_time)
        TextView it1Time;
        @BindView(R.id.it1_rc)
        RecyclerView it1Rc;
        @BindView(R.id.it1_bt)
        Button it1Bt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    //自定义事件
    public interface OnClickListener{
        void OnClick(String orderId);
    }
}
