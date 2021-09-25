package com.zxy.service.impl;

import com.zxy.mapper.UserMapper;
import com.zxy.model.Meta;
import com.zxy.model.User;
import com.zxy.service.UserService;
import com.zxy.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    //登录验证
    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPwd(user);
    }

    //用户数据列表
    @Override
    public Map<String,Object> userDataList(String query, Integer pagenum, Integer pagesize) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        int page = (pagenum - 1) * pagesize;
        int count = 0;
        List<Map<Object,Object>> list;
        try {
            //查询用户总条数
            count = userMapper.selectCount(query);
            //查询用户数据列表
            list = userMapper.selectByUserAndRole(query, page, pagesize);
        }catch (Exception e){
            e.printStackTrace();
            map.put("data",null);
            map.put("meta",500);
            return map;
        }
        data.put("totalpage",count);
        data.put("pagenum",pagenum);
        data.put("users",list);
        map.put("data",data);
        map.put("meta",new Meta("获取成功",200));
        return map;
    }

    //添加用户
    @Override
    public Map<String, Object> addUser(User user) {
        Map<String,Object> map = new HashMap<>();
        user.setRid(1);
        user.setCreatTime(DateTimeUtil.getSysTime());
        user.setState("0");
        user.setToken("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjUwMCwicmlkIjowLCJpYXQiOjE1MTI1NDQyOTksImV4cCI6MTUxMjYzMDY5OX0.eGrsrvwHm-tPsO9r_pxHIQ5i5L1kX9RX444uwnRGaIM");
        try {
            int i = userMapper.insertSelective(user);
            if (i > 0){
                map.put("data",user);
                map.put("meta",new Meta("用户创建成功",201));
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("data",null);
            map.put("meta",new Meta("用户创建失败",500));
        }
        map.put("data",null);
        map.put("meta",new Meta("用户创建失败",500));
        return map;
    }

    //修改用户状态
    @Override
    public Map<String, Object> updateState(Integer uId, boolean type) {
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        user.setId(uId);
        user.setState(type ? "1" : "0");
        try {
            int result = userMapper.updateByPrimaryKeySelective(user);
            if (result > 0){
                map.put("data",userMapper.selectByPrimaryKey(uId));
                map.put("meta",new Meta("设置状态成功",200));
            }
        }catch (Exception e){
            map.put("data",null);
            map.put("meta",new Meta("设置状态失败",500));
        }
        return map;
    }

    //根据 ID 查询用户信息
    @Override
    public Map<String,Object> queryUserById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        try {
            User user = userMapper.selectByPrimaryKey(id);
            map.put("data",user);
            map.put("meta",new Meta("查询成功",200));
        }catch (Exception e){
            map.put("data",null);
            map.put("meta",new Meta("查询失败",500));
        }
        return map;
    }

    //删除单个用户
    @Override
    public Map<String, Object> deleteUserById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("data",null);
        try {
            int result = userMapper.deleteByPrimaryKey(id);
            if (result > 0){
                map.put("meta",new Meta("删除成功",200));
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("meta",new Meta("删除失败",500));
        }
        return map;
    }

    //分配用户角色
    @Override
    public Map<String, Object> distributionUserState(User user) {
        Map<String,Object> map = new HashMap<>();
        try {
            int result = userMapper.updateByPrimaryKeySelective(user);
            if (result > 0){
                map.put("data",userMapper.selectByPrimaryKey(user.getId()));
                map.put("meta",new Meta("设置角色成功",200));
            }
        }catch (Exception e){
            map.put("data",null);
            map.put("meta",new Meta("设置角色失败",500));
        }
        return map;
    }

    @Override
    public Map<String, Object> updateUser(User user) {
        Map<String,Object> map = new HashMap<>();
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0){
            User user1 = userMapper.selectByPrimaryKey(user.getId());
            map.put("data",user1);
            map.put("meta",new Meta("更新成功",200));
            return map;
        }
        map.put("data",null);
        map.put("meta",new Meta("更新失败",500));
        return map;
    }
}
