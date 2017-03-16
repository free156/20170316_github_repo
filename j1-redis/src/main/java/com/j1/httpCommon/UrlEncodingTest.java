package com.j1.httpCommon;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by che2 on 2016/11/10.
 */
public class UrlEncodingTest {

    public static void  main(String[] args){
        try {
            String encode = URLEncoder.encode("我有一头小毛驴，但我从来也不骑", "UTF-8");
            System.out.println(encode);
            String decode = URLDecoder.decode( encode,"UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
