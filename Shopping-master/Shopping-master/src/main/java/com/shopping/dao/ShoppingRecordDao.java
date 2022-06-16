package com.shopping.dao;

import com.shopping.entity.ShoppingRecord;

import java.util.List;
//购物记录
public interface ShoppingRecordDao {
    ShoppingRecord getShoppingRecord(int userId, int productId,String time);

    //增加购物记录
    void addShoppingRecord(ShoppingRecord shoppingRecord);

    //删除购物记录
    boolean deleteShoppingRecord(int userId,int productId);

    //更新购物记录
    boolean updateShoppingRecord(ShoppingRecord shoppingRecord);
    //通过userID获取购物记录列表
    List<ShoppingRecord> getShoppingRecords(int userId);

    //获取全部购物记录列表
    List<ShoppingRecord> getAllShoppingRecords();
    //通过订单状态获取购物记录列表
    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    //获取当前用户的产品ID的购物记录
    boolean getUserProductRecord(int userId,int productId);

    //删除该用户的购物记录
    boolean deleteShoppingRecordByUser(int userId);

    //通过商品ID删除购物记录
    boolean deleteShoppingRecordByProductId(int productId);
}
