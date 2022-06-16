package com.shopping.dao;

import com.shopping.entity.UserDetail;

import java.util.List;


public interface UserDetailDao {

    //获取拥护详细信息
    public UserDetail getUserDetail(int id);

    //添加用户详细信息
    public void addUserDetail(UserDetail userDetail);

    //删除用户详细信息
    public boolean deleteUserDetail(int id);

    //更新用户详细信息
    public boolean updateUserDetail(UserDetail userDetail);

    //获取所有用户详细信息列表
    public List<UserDetail> getAllUserDetail();
}
