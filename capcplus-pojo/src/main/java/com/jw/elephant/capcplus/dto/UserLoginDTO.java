package com.jw.elephant.capcplus.dto;

import com.jw.elephant.capcplus.constant.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录DTO
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 密码强度等级：（根据满足一下条件进行划分：弱：0-1个，中：2-3，强：全部）
     *  1.包含至少1个大写和小写字母的组合
     *  2.包含三位数字
     *  3.包含三个特殊字符
     *  4.密码长度12位以上
     */
    private Integer level;

    /**
     * 记住我
     */
    private Boolean isRememberMe;
    /**
     *  用户登录校验
     *
     * @param userLoginDTO
     * @author zhangjie
     * @date 2022/5/25
     */
    public static void asserts(UserLoginDTO userLoginDTO){
        Assert.isBlank(userLoginDTO.getAccount(),"账号不能为空！");
        Assert.isBlank(userLoginDTO.getPassword(),"密码不能为空！");
        Assert.isBlank(userLoginDTO.getCode(),"验证码不能为空！");
    }

    /**
     * 为当前密码生成对应密码强度等级（目前有三级）
     * @param password
     * @return
     */
    private Integer genLevel(String password){
        Integer level = 0;
        //是否至少一个大小写字母组合
        //包含三位数
        return  level;
    }
}
