package com.bawei.hujintao.widget;
import com.bawei.hujintao.model.bean.LoginBean;
import com.bawei.hujintao.model.bean.OrderBean;
import com.bawei.hujintao.model.bean.PayBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/2/18 0018 上午 9:19
 */
public interface Api {
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<LoginBean> login(@Query("keyword")String key, @Query("page") int page, @Query("count")int count);
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<OrderBean> or(@Header("userId")int uid,@Header("sessionId")String sid,
                             @Query("status")int status,@Query("page")int page,@Query("count")int count);
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<PayBean> pay(@Header("userId")int uid,@Header("sessionId")String sid,@Query("orderId")String orderId,@Query("payType")int type);
}
