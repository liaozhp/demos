package com.jwt.define;


import java.util.HashMap;
import java.util.Map;

/**简单的模拟缓存实现
 * Created by lzp on 2017/9/17.
 */
public class Cache {
    private static final Map<String,User> map=new HashMap();

    static {
        User user=new User(1,"管理员","admin","pass");
        map.put(user.getLoginname(),user);
//        map.put("admin","pass");
    }
    public static void put(String key,User val){
        map.put(key, val);
    }
    public static void clear(){
        map.clear();
    }
    public static boolean containsKey(Object key){
        return map.containsKey(key);
    }
    public static User get(String key){
        return map.get(key);
    }
}
