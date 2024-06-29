package com.ls.ssm_exp.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2920:21
 */
public class EncodeTools {

    public static void main(String[] args) throws Exception {
        String password = "Mysql1122223";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];
        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }
}
