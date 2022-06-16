package com.shopping.dao;

import com.shopping.entity.User;

import java.util.List;


public interface UserDAO {
    //通过用户ID获取用户
    public User getUser(int id);

    //通过姓名或邮箱获取拥护
    public User getUser(String nameOrEmail);

    //添加用户
    public void addUser(User user);

    //删除用户
    public boolean deleteUser(int id);

    //更新用户
    public boolean updateUser(User user);

    //获取用户列表
    public List<User> getAllUser();

    //将用户升级为VIP
    public boolean becomeVip(int userId);
}
