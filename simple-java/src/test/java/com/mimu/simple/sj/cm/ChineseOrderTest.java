package com.mimu.simple.sj.cm;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * author: mimu
 * date: 2019/9/10
 */
public class ChineseOrderTest {
    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");

    public List<String> getList() {
        List<String> name = new ArrayList<>();
        name.add("好二");
        name.add("haiming");
        name.add("haoming");
        name.add("号字");
        name.add("韩$杨");
        name.add("翔子");
        name.add("安明");
        name.add("褚健");
        name.add(" he");
        return name;
    }

    @Test
    public void order() {
        List<String> name = getList();
        Collator collator = Collator.getInstance(Locale.CHINA);
        name = name.stream().sorted(collator::compare).collect(Collectors.toList());
        System.out.println(name);
    }

    @Test
    public void orderString() {
        List<String> name = getList();
        Collator collator = Collator.getInstance(Locale.CHINA);
        List<String> chinese =
                name.stream().filter(s -> Character.isLetter(getPinYinString(s).charAt(0))).collect(Collectors.toList());
        List<String> noneChinese = name.stream().filter(s -> !Character.isLetter(getPinYinString(s).charAt(0))).collect(Collectors.toList());
        chinese = chinese.stream().sorted(Comparator.comparing(this::getPinYinString)).collect(Collectors.toList());
        noneChinese = noneChinese.stream().sorted(collator::compare).collect(Collectors.toList());
        chinese.addAll(noneChinese);
        System.out.println(chinese);
    }

    private String getPinYinString(String china) {
        if (StringUtils.isEmpty(china)) {
            return "";
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        String firstLetter = china.trim().substring(0, 1);
        try {
            return pattern.matcher(firstLetter).find() ? PinyinHelper.toHanYuPinyinString(firstLetter, format, "", true).toLowerCase() : PinyinHelper.toHanYuPinyinString(firstLetter, format, "", true).toLowerCase() + "z";
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            return firstLetter;
        }
    }
}
