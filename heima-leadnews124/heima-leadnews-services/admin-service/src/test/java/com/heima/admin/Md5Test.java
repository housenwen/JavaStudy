package com.heima.admin;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * @作者 itcast
 * @创建日期 2021/5/23 14:29
 **/
public class Md5Test {

    @Test
    public void md5(){
        String salt = RandomStringUtils.randomAlphanumeric(8);
        System.out.println(salt);
        String s = DigestUtils.md5DigestAsHex(("hello123"+salt).getBytes());
        System.out.println(s);
    }
}
