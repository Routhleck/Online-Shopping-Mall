package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ShoppingCarDaoImplement implements ShoppingCarDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    //获取购物车
    public ShoppingCar getShoppingCar(int userId, int productId) {
        String hql = "from ShoppingCar where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return (ShoppingCar) query.uniqueResult();
    }

    @Override
    //添加购物车
    public void addShoppingCar(ShoppingCar shoppingCar) {
        sessionFactory.getCurrentSession().save(shoppingCar);
    }

    @Override
    //删除购物车
    public boolean deleteShoppingCar(int userId, int productId) {
        String hql = "delete ShoppingCar where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    //更新购物车
    public boolean updateShoppingCar(ShoppingCar shoppingCar) {
        String hql = "update ShoppingCar set productPrice=?,counts=? where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,shoppingCar.getProductPrice());
        query.setParameter(1,shoppingCar.getCounts());
        query.setParameter(2,shoppingCar.getUserId());
        query.setParameter(3,shoppingCar.getProductId());
        return query.executeUpdate() > 0;
    }

    @Override
    //通过用户id获取购物车
    public List<ShoppingCar> getShoppingCars(int userId) {
        String hql = "from ShoppingCar where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

    @Override
    //删除用户时删除购物车
    public boolean deleteShoppingCarByUser(int userId) {
        String hql = "delete ShoppingCar where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        return query.executeUpdate() > 0;
    }

    @Override
    //删除商品时删除购物车
    public boolean deleteShoppingCarByProduct(int productId) {
        System.out.println("deleteShoppingCarByProduct productId is "+productId);
        String hql = "delete ShoppingCar where productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, productId);
        return query.executeUpdate() > 0;
    }
}
