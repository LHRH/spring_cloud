package com.lh.cloud.tool;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: LH
 * @Description: 正则转换工具类
 * @Date: 14:50 2017/7/10
 */
public class RegExpValidatorUtils {
    /**
     * 过滤中文 英文 / ² _-
     */
    public static String CHINESE_ENGLISH = "[a-zA-Z\\u4e00-\\u9fa5\\/\\²\\-\\_\\（\\）\\(||)\\:\\'\\;\\㎡\\：\\、]";
    /**
     * 过滤中文
     */
    public static String CHINESE = "[\\u4e00-\\u9fa5]";
    /**
     * 过滤英文数字
     */
    public static String ENGLISHNUMBER = "[^0-9a-zA-Z]";

    public static String NUMBER="[^0-9]";

    public static String DOUBLE="[^0-9\\.]";

    /**
     * 验证网址Url
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 字符串过滤
     *
     * @param str 字符串
     * @param reg 正则
     * @return
     */
    public static String filter(String str, String reg) {
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
    }

    /**
     * 提取中文
     *
     * @param paramValue
     * @return
     */
    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            stringBuilder.append(matcher.group(0)).append(" ");
        }
        if (!StringUtils.isEmpty(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * 提取数字(数字比较长)
     *
     * @param paramValue
     * @return
     */
    public static Long getNumLong(String paramValue) {
        String filter = RegExpValidatorUtils.filter(paramValue, RegExpValidatorUtils.NUMBER);
        if (!StringUtils.isEmpty(filter) && filter.trim().length() != 0) {
            try {
                return Long.parseLong(filter.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 提取数字（短整型）
     *
     * @param paramValue
     * @return
     */
    public static Integer getNumInt(String paramValue) {
        String filter = RegExpValidatorUtils.filter(paramValue, RegExpValidatorUtils.NUMBER);
        if (!StringUtils.isEmpty(filter) && filter.trim().length() != 0) {
            try {
                return Integer.parseInt(filter.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 提取数字 返回double
     *
     * @param paramValue
     * @return
     */
    public static Double getNumDouble(String paramValue) {
        String filter = RegExpValidatorUtils.filter(paramValue, RegExpValidatorUtils.DOUBLE);
        if (!StringUtils.isEmpty(filter) && filter.trim().length() != 0) {
            return Double.parseDouble(filter.trim());
        }
        return null;
    }

    /**
     * 提取数字 字母
     *
     * @param paramValue
     * @return
     */
    public static String getEnglistNum(String paramValue) {
        if (!StringUtils.isEmpty(paramValue)) {
            return RegExpValidatorUtils.filter(paramValue, RegExpValidatorUtils.ENGLISHNUMBER);
        }
        return null;
    }

    /**
     * 提取正则表达式匹配的值
     * @param val 需要匹配的值
     * @param reg 正则
     * @return
     */
    public static String getValueByReg(String val, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(val);
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }
    /**
     * 判断该字符串是否为中文
     *
     * @param string
     * @return
     */
    public static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (19968 <= n && n < 40869) {
                return true;
            }
        }
        return false;
    }
}
