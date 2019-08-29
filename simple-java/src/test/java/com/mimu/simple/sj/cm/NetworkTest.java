package com.mimu.simple.sj.cm;


import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetSocketAddress;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class NetworkTest {
    public static void main(String[] args) {
        while (true){
            InetSocketAddress address = new InetSocketAddress("localhost",9090);
            System.out.println(address.getAddress());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDecript(String key, String decMessage) {
        if (StringUtils.isEmpty(decMessage) || StringUtils.isEmpty(key)) {
            return "";
        }
        byte[] keyByte = key.substring(0, 16).getBytes();
        SecretKey secretKey = new SecretKeySpec(keyByte, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decode = Hex.decode(decMessage);
            byte[] bytes = cipher.doFinal(decode);
            return new String(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
        }
        return "";
    }

    public String getEncript(String key, String originMessage) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(originMessage)) {
            return "";
        }
        byte[] keyByte = key.substring(0, 16).getBytes();
        SecretKey secretKey = new SecretKeySpec(keyByte, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(originMessage.getBytes());
            return Hex.encode2Str(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
        }
        return null;
    }
}
