package com.mimu.simple.sj.cm;


import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang3.StringUtils;

import java.awt.event.ItemEvent;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * author: mimu
 * date: 2019/9/3
 */
public class CodeInit {

    private static String originCode = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static char[] CODEARRY = originCode.toCharArray();
    private static String zore11 = "00000000000";

    public static String getCode(long id) {
        StringBuilder codeBuilder = new StringBuilder();
        while (id > 0) {
            int mod = Math.toIntExact(id % 61);
            id = (id - mod) / 61;
            codeBuilder.append(CODEARRY[mod]);
        }
        String code = codeBuilder.toString();
        // return code.length() < 11 ? code + zore11.substring(code.length()) : code;
        return code;
    }

    public static long deCode(String realCode) {
        //String realCode = code.replaceAll("0", "");
        char[] codeArry = realCode.toCharArray();
        //System.out.println(realCode);
        long result = 0;
        for (int i = 0; i < realCode.length(); i++) {
            result += originCode.indexOf(String.valueOf(codeArry[i])) * Math.pow(61.0, (double) i);
        }
        return result;
    }

    public static void main(String[] args) {
        /*System.out.println(getCode(8555287229450072081l));
        System.out.println(getCode(6290494046431780000l));
        System.out.println(deCode("GkPYj8ROAZc"));
        System.out.println(deCode("LZs7jxD1VO9"));*/
        // System.out.println(deCode("d3000000000"));
        //System.out.println(6555287229450072081l%16);
        /*try {
            System.out.println(PinyinHelper.toHanYuPinyinString("张1名",new HanyuPinyinOutputFormat(),"",true));
            System.out.println(PinyinHelper.toHanYuPinyinString("$张名",new HanyuPinyinOutputFormat(),"",false));
            System.out.println(PinyinHelper.toHanYuPinyinString("d张ming",new HanyuPinyinOutputFormat(),"",true));
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }*/
        //System.out.println(order(getList()));
        System.out.println(orderString(getList()));

    }

    public static List<String> getList() {
        List<String> name = new ArrayList<>();
        name.add("1 黄秋龙");
        name.add("1京秋龙");
        name.add("$黄秋龙");
        name.add("韩杨");
        name.add("haoming");
        name.add("韩$杨");
        name.add("翔子");
        name.add("安明");
        name.add("褚健");
        return name;
    }

    public static List<String> order(List<String> name) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        return name.stream().sorted((o1, o2) -> collator.compare(o1, o2)).collect(Collectors.toList());
    }

    public static List<String> orderString(List<String> name) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        name.stream().forEach(item -> System.out.println(getPinYinString(item)));
        List<String> chinese =
                name.stream().filter(s -> Character.isLetter(getPinYinString(s).charAt(0))).collect(Collectors.toList());
        List<String> noneChinese = name.stream().filter(s -> !Character.isLetter(getPinYinString(s).charAt(0))).collect(Collectors.toList());
        chinese = chinese.stream().sorted(Comparator.comparing(CodeInit::getPinYinString)).collect(Collectors.toList());
        noneChinese = noneChinese.stream().sorted((o1, o2) -> collator.compare(o1, o2)).collect(Collectors.toList());
        chinese.addAll(noneChinese);
        return chinese;
    }

    public static String getPinYinString(String china) {
        if (StringUtils.isEmpty(china)) {
            return "";
        }
        try {
            return PinyinHelper.convertToPinyinString(china, "", PinyinFormat.WITHOUT_TONE);
        } catch (PinyinException e) {
            return china;
        }
    }

}
