package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordOrderStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordPayStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRecord {

    /**
     * id
     *
     */
    private Long id;
    /**
     * 归属人
     */
    private Long userId;

    /**
     * 订单编号
     *
     */
    private String orderNumber;

    /**
     *  充值金额
     */
    private BigDecimal balance;


    /**
     *  充值类型
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private RechargeRecordTypeEnum type;


    /**
     *  备注信息
     */
    private String remark;

    /**
     *  支付流水号
     *
     */
    private String paySerialNumber;

    /**
     *  订单状态
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private RechargeRecordOrderStatusEnum orderStatus;

    /**
     *  支付状态：0：等待支付，1：支付成功，2：支付失败
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private RechargeRecordPayStatusEnum payStatus;


    /**
     * 提交时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date submitTime;

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
