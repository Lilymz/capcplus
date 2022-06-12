package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.ServerDetailResultTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.ServerDetailSupplierTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务详情
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDetail {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 服务id
     */
    private Long serverId;

    /**
     * 认证id(数据库id)
     */
    private Long appId;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     *  接收手机号
     */
    private String targetPhone;
    /**
     * 消费金额
     */
    private BigDecimal cost;
    /**
     * 返回码类型
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ServerDetailResultTypeEnum resultType;
    /**
     * 返回值
     */
    private String result;

    /**
     * 运营商属性
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ServerDetailSupplierTypeEnum supplierType;
    /**
     * 请求
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date requestTime;
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
