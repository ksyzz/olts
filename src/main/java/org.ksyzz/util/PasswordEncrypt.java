package org.ksyzz.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密密码
 * Created by fengqian on 2017/4/13.
 */
public class PasswordEncrypt {
    public static String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        return str;
    }

    public static boolean isEqual(String new_password, String old_password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (old_password.equals(encrypt(new_password)))
            return true;
        else
            return false;
    }
}
