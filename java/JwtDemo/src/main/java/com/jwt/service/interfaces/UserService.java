package com.jwt.service.interfaces;

import com.jwt.define.ResultBean;

/**
 * Created by lzp on 2017/9/17.
 */

public interface UserService {

    ResultBean login(String loginname,String loginpwd);

    ResultBean validateLoginInfo(String loginname,String  loginpwd);

    ResultBean getUserInfo(String loginname);
}
