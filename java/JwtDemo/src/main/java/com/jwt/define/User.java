package com.jwt.define;

/**
 * Created by lzp on 2017/9/21.
 */
public class User {
    private Integer id;
    private String username;
    private String loginname;
    private String loginpwd;

    public User(Integer id, String username, String loginname, String loginpwd) {
        this.id = id;
        this.username = username;
        this.loginname = loginname;
        this.loginpwd = loginpwd;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginname='" + loginname + '\'' +
                ", loginpwd='" + loginpwd + '\'' +
                '}';
    }
}
