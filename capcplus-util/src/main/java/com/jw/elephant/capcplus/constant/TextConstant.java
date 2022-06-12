package com.jw.elephant.capcplus.constant;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.List;

/**
 * 文本常量类
 *
 * @author zhangjie
 */
public class TextConstant {
    /**
     * 当前验证码成语集合
     */
    public static final List<String> PHRASE;
    /**
     * 非法字符
     */
    public static final List<String> ILLEGAL_STRINGS;

    /**
     * 验证码key
     */
    public static final String CODE_KEY="CODE_KEY";
    static {
        PHRASE = Arrays.asList("走马观花", "莺歌燕舞", "贪生怕死", "瓜田李下", "骑马找马", "取长补短", "马到成功", "满面春风",
                "独树一帜", "枯木逢春", "繁荣昌盛", "枝繁叶茂", "焦头烂额", "漫山遍野", "刨根问底", "黄粱美梦", "闻名遐迩",
                "全神贯注", "掩耳盗铃", "震耳欲聋", "响彻云霄", "斩钉截铁", "才华横溢", "举案齐眉", "春意盎然", "饱经忧患",
                "封妻荫子", "本末倒置", "厚此薄彼", "扭转乾坤");
        ILLEGAL_STRINGS = Arrays.asList("~","`","%","&","*",",",".","?","？");
    }
    /**
     * MD5盐
     */
    public static final String MD5_SALT = "capcplus";

    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("MD5", "123456",ByteSource.Util.bytes(TextConstant.MD5_SALT),5);
        System.out.println(simpleHash);
    }
    public static final String ROOT="root";

    public static final String EAMIL_SUBJECT="找回密码";
    /**
     * 存储验证码的key
     */
    public static final String EMAIL_CODE="EMAIL_CODE";

    /**
     * 当前登录用户
     */
    public static final String USER="user";
    /**
     * 图片存储路径
     */
    public static final String IMG_FOLDER="/resources/images/";
    /**
     * 文件上传存储路径
     */
    public static final String UPLOADER_IMG_FLODER="images";
    /**
     * KB
     */
    public static final int KB = 1024;
    /**
     * MB
     */
    public static final int MB = 1024*1024;
}
