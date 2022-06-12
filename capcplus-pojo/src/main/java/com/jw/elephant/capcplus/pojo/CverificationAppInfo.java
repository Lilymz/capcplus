package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoAppStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoCurStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CverificationAppInfoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 统一认证信息
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CverificationAppInfo {
    /**
     * id
     */
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 应用所属平台
     */
    private String appPlatform;

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 应用包名
     */
    private String appPackage;
    /**
     * 应用登录全类名
     */
    private String appLoginClazz;
    /**
     * 应用签名
     */
    private String appSign;
    /**
     * 应用签名类型
     */
    private Integer appSignType;
    /**
     * 应用logo
     */
    private String appLogoUrl;
    /**
     *  应用描述
     */
    private String appDescription;
    /**
     * 应用业务id
     */
    private String systemYwIds;
    /**
     * 应用状态
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CverificationAppInfoAppStatusEnum appStatus;
    /**
     * 应用审核状态
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CverificationAppInfoStatusEnum auditStatus;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 当前状态
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CverificationAppInfoCurStatusEnum CurStatus;
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
