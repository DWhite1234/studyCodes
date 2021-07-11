package com.atguigu;

import java.io.Serializable;

public class FinalVariable implements Serializable {
    public static final String GMALL_STARTUP = "GMALL_STARTUP";

    public static final String KAFKA_TOPIC_ORDER="GMALL_ORDER";

    public static final String KAFKA_TOPIC_EVENT="GMALL_EVENT";

    public static final String ES_ALERT = "gmall_coupon_alert";

    //订单明细表主题
    public static final String KAFKA_TOPIC_ORDER_DETAIL = "TOPIC_ORDER_DETAIL";

    //用户表主题
    public static final String KAFKA_TOPIC_USER = "TOPIC_USER_INFO";

    //灵活分析索引前缀
    public static final String ES_SALE_DETAIL = "gmall2021_sale_detail";
}
