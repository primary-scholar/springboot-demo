package com.mimu.simple.sj.cm;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * author: mimu
 * date: 2019/9/10
 */
public class DEncryptTest {
    private static String originCode = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] CODEARRY = originCode.toCharArray();

    @Test
    public void getCode() {
        long id = 6290494046431785000l;
        StringBuilder codeBuilder = new StringBuilder();
        while (id > 0) {
            int mod = Math.toIntExact(id % 61);
            id = (id - mod) / 61;
            codeBuilder.append(CODEARRY[mod]);
        }
        System.out.println(codeBuilder.toString());
    }

    @Test
    public void deCode() {
        String realCode = "Jlu7jxD1VO9";
        char[] codeArry = realCode.toCharArray();
        long result = 0;
        for (int i = 0; i < realCode.length(); i++) {
            result += originCode.indexOf(String.valueOf(codeArry[i])) * Math.pow(61.0, (double) i);
        }
        System.out.println(result);
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

    public static class Hex {
        public static String encode2Str(byte[] bytes) {
            return null;
        }

        public static byte[] decode(String message) {
            return null;
        }
    }
}
