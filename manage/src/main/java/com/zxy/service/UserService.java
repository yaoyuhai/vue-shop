package com.zxy.service;

import com.zxy.model.User;

import java.util.Map;

public interface UserService {

    User login(User user);

    Map<String,Object> userDataList(String query, Integer pagenum, Integer pagesize);

    Map<String, Object> addUser(User user);

    Map<String, Object> updateState(Integer uId, boolean type);

    Map<String,Object> queryUserById(Integer id);

    Map<String, Object> deleteUserById(Integer id);

    Map<String, Object> distributionUserState(User user);

    Map<String, Object> updateUser(User user);
}
