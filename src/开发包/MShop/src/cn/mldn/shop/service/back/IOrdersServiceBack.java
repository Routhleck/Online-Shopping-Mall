package cn.mldn.shop.service.back;

import cn.mldn.shop.vo.Orders;

import java.util.Map;

public interface IOrdersServiceBack {
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
    public Orders show(int oid) throws Exception ;
}
