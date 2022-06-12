package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.CompanyAuditStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyPayTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 企业信息表
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 单位类型
     * 0：企业：FIRM
     * 1：事业单位：PUBLIC_INSTITUTION
     * 2：民办非企业单位：PEOPLE_NOT_PUBLIC_INSTITUTION
     * 3：个体工商户：OWNER_FIRM
     * 4：社会团体 SOCIAL_GROUPS
     * 5：党政及国家机关 GOVERNMENT
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CompanyTypeEnum type;
    /**
     * 企业(单位)名称
     */
    private String name;
    /**
     * 企业所属用户ID
     */
    private Long userId;
    /**
     * 注册号或证号，全局唯一
     */
    private String registerCode;
    /**
     * 组织机构代码，可为空
     */
    private String companyCode;
    /**
     * 企业级别
     */
    private String companyLevel;
    /**
     * 企业域名
     */
    private String companyDomain;
    /**
     * 地址
     */
    private String companyAddress;
    /**
     * 运营范围
     */
    private String companyScope;
    /**
     * 有效期，长期值：2999-12-31
     */
    private String dueDate;
    /**
     * 企业固定电话（如：0595-23359110）,全局唯一
     */
    private String telPhone;
    /**
     * 审核通知手机
     */
    private String tempPhone;
    /**
     * 审核状态。
     * 0：未提交(默认)
     * 1：审核通过
     * 2：审核拒绝
     * 3：待审核
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CompanyAuditStatusEnum auditStatus;
    /**
     * 审核原因
     */
    private Integer auditReason;
    /**
     * 最后审核人ID
     */
    private Integer adminId;
    /**
     * 企业最大坐席数(默认100)
     */
    private Integer maxExt;
    /**
     * 企业有效状态 0：正常; 1：异常;(余额异常) 2：系统异常(管理员操作)
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CompanyStatusEnum companyStatus;
    /**
     * 付费类型,0:预付费(默认),1:后付费
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private CompanyPayTypeEnum payType;
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
