package com.mimu.simple.sj.cm;

/**
 * author: mimu
 * date: 2019/9/3
 */
public class CodeInit {

    private static String[] CODEARRY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    private static String zore11 = "00000000000";

    public static String getCode(long id) {
        StringBuilder codeBuilder = new StringBuilder();
        while (id > 0) {
            int mod = Math.toIntExact(id % 62);
            id = (id - mod) / 62;
            codeBuilder.append(CODEARRY[mod]);
        }
        String code = codeBuilder.toString();
        return code.length() < 11 ? code + zore11.substring(code.length()) : code;
    }

    public static void main(String[] args) {
        System.out.println(getCode(6290494046431785000l));
        System.out.println(getCode(6290494946431785001l));
        System.out.println(getCode(1379288680049586287l));
        System.out.println(getCode(999l));
    }
}
