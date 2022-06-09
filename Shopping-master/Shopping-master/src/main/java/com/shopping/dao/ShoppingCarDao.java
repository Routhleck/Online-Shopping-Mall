package com.shopping.dao;

import com.shopping.entity.ShoppingCar;

import java.util.List;


public interface ShoppingCarDao {
    ShoppingCar getShoppingCar(int userId,int productId);



    void addShoppingCar(ShoppingCar shoppingCar);

    boolean deleteShoppingCar(int userId,int productId);

    boolean updateShoppingCar(ShoppingCar shoppingCar);

    List<ShoppingCar> getShoppingCars(int userId);

    boolean deleteShoppingCarByUser(int userId);

    boolean deleteShoppingCarByProduct(int productId);

}
