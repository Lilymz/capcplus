package com.jw.elephant.capcplus.util;

import java.util.regex.Pattern;

public class CommonUtil {

    /**
     * 正则验证工具
     *
     * @author zj
     * @date 2022/03/01 09:52
     **/
    public static class RegexValidatorUtil {
        /**
         * 正则表达式：验证用户名
         */
        public static final String REGEX_USERNAME = "^[a-z0-9A-Z]\\w{7,18}$";

        /**
         * 正则表达式：验证密码
         */
        public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

        /**
         * 正则表达式：验证手机号
         */
        public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

        /**
         * 正则表达式：验证邮箱
         */
        public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        /**
         * 正则表达式：验证汉字
         */
        public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

        /**
         * 正则表达式：验证身份证
         */
        public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

        /**
         * 正则表达式：验证URL
         */
        public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

        /**
         * 正则表达式：验证IP地址
         */
        public static final String REGEX_IP_ADDR = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        /**
         * 校验用户名
         */
        public static boolean isUsername(String username) {
            return Pattern.matches(REGEX_USERNAME, username);
        }

        /**
         * 校验密码
         */
        public static boolean isPassword(String password) {
            return Pattern.matches(REGEX_PASSWORD, password);
        }

        /**
         * 校验手机号
         */
        public static boolean isMobile(String mobile) {
            return Pattern.matches(REGEX_MOBILE, mobile);
        }

        /**
         * 校验邮箱
         */
        public static boolean isEmail(String email) {
            return Pattern.matches(REGEX_EMAIL, email);
        }

        /**
         * 校验汉字
         */
        public static boolean isChinese(String chinese) {
            return Pattern.matches(REGEX_CHINESE, chinese);
        }

        /**
         * 校验身份证
         */
        public static boolean isIDCard(String idCard) {
            return Pattern.matches(REGEX_ID_CARD, idCard);
        }

        /**
         * 校验URL
         */
        public static boolean isUrl(String url) {
            return Pattern.matches(REGEX_URL, url);
        }

        /**
         * 校验IP地址
         */
        public static boolean isIPAddr(String ipAddr) {
            return Pattern.matches(REGEX_IP_ADDR, ipAddr);
        }
    }
}
