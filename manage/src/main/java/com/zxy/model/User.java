package com.zxy.model;

import jdk.nashorn.internal.parser.Token;

public class User {
    private Integer id;

    private String password;

    private Integer rid;

    private String username;

    private String mobile;

    private String email;

    private String creatTime;

    private String state;

    private String token;

    public User() {
    }

    public User(Integer id, String password, Integer rid, String username, String mobile, String email, String creatTime, String state,String token) {
        this.id = id;
        this.password = password;
        this.rid = rid;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.creatTime = creatTime;
        this.state = state;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", rid=" + rid +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}