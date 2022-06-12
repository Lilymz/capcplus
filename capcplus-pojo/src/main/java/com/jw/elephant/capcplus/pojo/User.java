package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.UserStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.UserSuperEnum;
import com.jw.elephant.capcplus.pojo.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户pojo
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 预警余额
     */
    private BigDecimal balanceWarn;
    /**
     * 头像
     */
    private String headPic;
    /**
     * 用户类型
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private UserTypeEnum type;
    /**
     * 用户状态：1.禁用
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private UserStatusEnum status;
    /**
     * 是否超级用户：0：不是，1是 （用于授权）
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private UserSuperEnum isSuper;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 最后登录时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date lastLoginTime;
    /**
     * 创建时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;
    /**
     * 更新时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date updateTime;

    @TableLogic
    private Integer isDel;
}
