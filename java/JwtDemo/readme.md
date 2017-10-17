# JWT demo
json web token(JWT)是一个轻量级的用于身份验证的框架，基于jwt可方便地
实现单点登录（sso）。jwt提供了简便的基于token的鉴权机制，具体定义
可参考[什么是JWT](http://www.jianshu.com/p/576dbf44b2ae)，JWT项目
可参见[JJWT](https://github.com/jwtk/jjwt)。
## 开发环境
servlet3.1:需支持servlet3.0语法;  
spring4.3.1:支持getMapping等语法.
## 添加jwt依赖
```
    <!--java json web token dependency-->
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt</artifactId>
      <version>0.7.0</version>
    </dependency>
```    
## jwt生成及校验token
核心类如下：
```
package com.jwt.util;

import com.jwt.define.Constants;
import com.jwt.define.ResultBean;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class TokenUtil {
    private static final Key key=MacProvider.generateKey();
    private static final long EXPIRATION_TIME=1000*60*60;
    private static final String subject="subject";

    public static String generateToken(Map<String,Object> map){
        System.out.println("密钥加密方式为"+key.getAlgorithm());         //此密钥加密方式为HmacSHA512
        long time=System.currentTimeMillis();
        String compactJwt= Jwts.builder()
                            .setClaims(map)
                            .setSubject(subject)
                            .setExpiration(new Date(time+EXPIRATION_TIME))     //设置token过期时间
                            .compressWith(CompressionCodecs.GZIP)        //设置token压缩格式
                            .signWith(SignatureAlgorithm.HS512,key)      //设置数字签名，数字签名加密方式需要与key加密方式一致,否则会报错
                            .compact();
        return compactJwt;
    }

    public static ResultBean validateToken(String token){
        ResultBean result=new ResultBean();
        if (token==null||"".equals(token)){
            result.setState(Constants.INVALID);
            result.setMsg("token不存在");
            return result;
        }
        try {
            Jws<Claims> jws=Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            if (jws.getBody().getSubject().equals(subject)){
                result.setState(Constants.VALID);
                result.setMsg("token有效");
            }
            long now=System.currentTimeMillis();
            long exp=jws.getBody().getExpiration().getTime();
            if (now>exp){
                result.setState(Constants.EXPIRED);
                result.setMsg("token已过期");
            }
        } catch (SignatureException e) {
            result.setState(Constants.INVALID);
            result.setMsg("token不合法");
        }
        return result;
    }
}
```
## 添加token验证过滤器
使用filter实现token验证过滤，api访问需携带token进行身份验证。
简单代码如下：
```
        String token=request.getHeader("token");

        ResultBean result=TokenUtil.validateToken(token);
        if (result.getState().equals(Constants.VALID)){
            System.out.println("token校验合法");
            chain.doFilter(request,response);
        }
```        
 ## token存储
使用session认证是将session存储于服务器端，而使用jwt的token鉴权
机制将token数据存在客户端。在登录时生成token，保存到本地，之后
每次调用api时携带token进行验证。页面存储token代码如下：
```
  $.ajax({
             type: "post",
             url: "/JwtDemo/userLogin",
             data: JSON.stringify(data),
             contentType: "application/json;charset=utf-8",
             success: function (resp) {
                 if (resp.state=="success") {
                     //将token存在本地存储，然后跳转到主页面
                     localStorage.setItem('token', resp.msg);
                     location.href = "main.html";
                 }
             }
         });
```        
 