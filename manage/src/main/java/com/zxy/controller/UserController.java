package com.zxy.controller;

import com.zxy.model.Meta;
import com.zxy.model.User;
import com.zxy.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    //登录验证
    @PostMapping("/login")
    public Map<String,Object> login(HttpServletRequest request, String username, String password){
        Map<String,Object> map = new HashMap<>();
        //处理参数
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用业务层的登录验证方法
        User user1 = userService.login(user);
        if (user1 != null){
            request.getSession().setAttribute("user",user1);
            map.put("data",user1);
            map.put("meta",new Meta("登录成功",200));
            return map;
        }
        map.put("data",null);
        map.put("meta",new Meta("登录失败",403));
        return map;
    }

    //展示数据
    @GetMapping("/users")
    public Map<String,Object> userDataList(String query, String pagenum, String pagesize){
        return userService.userDataList(query,Integer.valueOf(pagenum),Integer.valueOf(pagesize));
    }

    //添加用户
    @PostMapping("/users")
    public Map<String,Object> addUser(User user){
        Map<String ,Object> map;
        if (user.getUsername() == null || user.getPassword() == null){
            map = new HashMap<>();
            map.put("data",null);
            map.put("meta",new Meta("用户创建失败",403));
        }else {
            map = userService.addUser(user);
        }
        return map;
    }

    //修改用户状态
    @PutMapping("/users/{uId}/state/{type}")
    public Map<String,Object> updateState(@PathVariable("uId") Integer uId, @PathVariable("type") boolean type){
        return userService.updateState(uId,type);
    }

    //根据 ID 查询用户信息
    @GetMapping("/users/{id}")
    public Map<String,Object> queryUserById(@PathVariable("id") Integer id){
        return userService.queryUserById(id);
    }

    //编辑用户提交
    @GetMapping("/user")
    public Map<String,Object> updateUser(User user){
        Map<String,Object> map = userService.updateUser(user);
        return map;
    }

    //删除单个用户
    @DeleteMapping("/users/{id}")
    public Map<String,Object> deleteUserById(@PathVariable("id") Integer id){
        return userService.deleteUserById(id);
    }

    //分配用户角色
    @PutMapping("/users/{id}/{rid}")
    public Map<String,Object> distributionUserState(@PathVariable("id") Integer id,@PathVariable("rid") Integer rid){
        User user = new User();
        user.setId(id);
        user.setRid(rid);
        return userService.distributionUserState(user);
    }
}
