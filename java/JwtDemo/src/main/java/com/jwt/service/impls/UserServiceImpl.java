package com.jwt.service.impls;

import com.jwt.define.Cache;
import com.jwt.define.Constants;
import com.jwt.define.ResultBean;
import com.jwt.define.User;
import com.jwt.service.interfaces.UserService;
import com.jwt.util.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzp on 2017/9/17.
 */
@Service
public class UserServiceImpl implements UserService{
    public ResultBean login(String loginname, String loginpwd) {
        ResultBean result=validateLoginInfo(loginname,loginpwd);

        if (result.getState().equals(Constants.FAIL)){
            return result;
        }
        Map tokenMap=new HashMap();
        tokenMap.put("loginname",loginname);
        tokenMap.put("loginpwd",loginpwd);
        tokenMap.put("time",new Date());
        result.setMsg(TokenUtil.generateToken(tokenMap));

        return result;
    }

    public ResultBean validateLoginInfo(String loginname, String loginpwd) {
        ResultBean result=new ResultBean();

        result.setState(Constants.FAIL);
        if (Cache.containsKey(loginname)){
            if (Cache.get(loginname).getLoginpwd().equals(loginpwd)){
                result.setState(Constants.SUCCESS);
                result.setMsg("登录成功");
            }else {
                result.setMsg("密码错误");
            }
        }else {
            result.setMsg("登录名不正确");
        }
        return result;
    }

    public ResultBean getUserInfo(String loginname) {

        User user=Cache.get(loginname);
        ResultBean result=new ResultBean(Constants.SUCCESS,user);
        if (user==null){
            result.setState(Constants.FAIL);
        }
        System.out.println(result);
        return result;
    }
}
