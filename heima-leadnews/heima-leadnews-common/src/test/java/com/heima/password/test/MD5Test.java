package com.heima.password.test;

import com.heima.utils.common.BCrypt;
import com.heima.utils.common.MD5Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

public class MD5Test {

    public static void main(String[] args) {
        //md5加密  DegestUtils：spring框架提供的工具类
        //String md5Str = DigestUtils.md5DigestAsHex("abc".getBytes());
        //System.out.println(md5Str);//900150983cd24fb0d6963f7d28e17f72

        //uername:zhangsan  password:123   salt:随时字符串
       /* String salt = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(salt);
        String pswd = "123"+salt;

        String saltPswd = DigestUtils.md5DigestAsHex(pswd.getBytes());
        System.out.println(saltPswd);*/

       /* String gensalt = BCrypt.gensalt();//这个是盐  29个字符，随机生成
        System.out.println(gensalt);
        String password = BCrypt.hashpw("123456", gensalt);  //根据盐对密码进行加密
        System.out.println(password);//加密后的字符串前29位就是盐*/


        String abc = MD5Utils.encodeWithSalt("admin", "123abc");
        System.out.println(abc);
    }
}
