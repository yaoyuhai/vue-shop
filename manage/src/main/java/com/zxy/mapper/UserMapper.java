package com.zxy.mapper;

import com.zxy.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据 username 和 password 查询用户信息
    User selectByUsernameAndPwd(User user);

    //查询用户信息(多表联查，关联角色表)
    List<Map<Object,Object>> selectByUserAndRole(@Param("query") String query, @Param("pagenum") Integer pagenum, @Param("pagesize") Integer pagesize);

    //查询用户总条数
    int selectCount(@Param("query") String query);
}