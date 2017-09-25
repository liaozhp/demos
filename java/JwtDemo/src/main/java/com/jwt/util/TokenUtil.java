package com.jwt.util;

import com.jwt.define.Constants;
import com.jwt.define.ResultBean;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzp on 2017/9/14.
 */
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
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("username","admin");
        map.put("userpwd","pass");
        String token=TokenUtil.generateToken(map);
        System.out.println(token);
//        token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VycHdkIn0.u1qCjbhr7U-nYtxrYZrvMXCgHlISLEfalUPj7dkNREIs7Ph1hyJOSvkFp9QTF1hHKj5451EFlt4kwA4P7HgTM";
        System.out.println(validateToken(token));
    }
}
