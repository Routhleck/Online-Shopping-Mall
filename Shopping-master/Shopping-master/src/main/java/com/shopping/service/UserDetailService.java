package com.shopping.service;

import com.shopping.entity.UserDetail;

import java.util.List;


public interface UserDetailService {
    public UserDetail getUserDetail(int id);

    public void addUserDetail(UserDetail userDetail);

    public boolean deleteUserDetail(int id);

    public boolean updateUserDetail(UserDetail userDetail);

    public List<UserDetail> getAllUserDetail();
}
