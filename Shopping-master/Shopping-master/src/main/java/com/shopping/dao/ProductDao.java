package com.shopping.dao;

import com.shopping.entity.Product;

import java.util.List;

//接口ProductDao
public interface ProductDao {
    //通过id号码获取产品
    public Product getProduct(int id);
    //通过Boss的id来获取产品
    public List<Product> getProductsByBoss(int bossId);

    //通过产品的name获取产品
    public Product getProduct(String name);

    //添加产品
    public void addProduct(Product product);

    //删除产品，通过产品id
    public boolean deleteProduct(int id);

    //更新产品
    public boolean updateProduct(Product product);

    //通过关键词获取商品
    public List<Product> getProductsByKeyWord(String searchKeyWord);

    //得到商品的类型
    public List<Product> getProductsByType(int type);

    //得到所有的商品列表
    public List<Product> getAllProduct();


}
