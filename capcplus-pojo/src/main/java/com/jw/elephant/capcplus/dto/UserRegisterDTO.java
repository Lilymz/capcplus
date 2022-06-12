package com.jw.elephant.capcplus.dto;

import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户注册DTO
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String name;

    private String account;

    private String password;

    private String company;

    private String email;

    private String phone;

    /************************************************** 校验 ******************************************/
    /**
     * 注册参数校验
     *
     * @param registerDTO
     * @author zhangjie
     * @date 2022/5/26
     */
    public static void asserts(UserRegisterDTO registerDTO){
        Assert.isTrue(StringUtils.isBlank(registerDTO.getName()),"请填写用户名称！");
        Assert.isTrue(StringUtils.isBlank(registerDTO.getAccount()),"请填写账号！");
        /*  Assert.notTrue(CommonUtil.RegexValidatorUtil.isUsername(registerDTO.getAccount()),"非法账号,不可包含特殊字符");*/
        Assert.isTrue(StringUtils.isBlank(registerDTO.getPassword()),"请输入密码！");
        Assert.isTrue(registerDTO.getPassword().length()<8,"请输入至少8位-18密码！");
        Assert.isTrue(StringUtils.isBlank(registerDTO.getCompany()),"请填写公司名称！");
        Assert.notTrue(registerDTO.getCompany().length()<30,"本系统公司长度限制30位长度！");
        Assert.isBlank(registerDTO.getEmail(),"邮箱为空！");
        Assert.notTrue(CommonUtil.RegexValidatorUtil.isEmail(registerDTO.getEmail()),"请填写正确邮箱！");
        Assert.isBlank(registerDTO.getPhone(),"手机为空！");
        Assert.isTrue(CommonUtil.RegexValidatorUtil.isMobile(registerDTO.getPhone()),"请填写正确手机号！");
    }
}
