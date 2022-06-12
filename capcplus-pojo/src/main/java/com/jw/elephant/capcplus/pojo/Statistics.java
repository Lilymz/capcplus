package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据统计
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 归属人
     */
    private Long userId;
    /**
     * 服务名称
     */
    private Long serverId;

    /**
     * 总使用次数
     */
    private Long useTotal;

    /**
     * 总成功次数
     */
    private Long successTotal;

    /**
     * 未知次数
     */
    private Long unknownTotal;

    /**
     * 失败使用量
     */
    private Long failTotal;

    /**
     * 总花费
     */
    private BigDecimal costTotal;

    /**
     * 测试总花费
     */
    private BigDecimal devCostTotal;

    /**
     * 返现费用
     */
    private BigDecimal backTotal;

    /**
     * 实际费用
     */
    private BigDecimal realTotal;

    /**
     * 结算总价
     */
    private BigDecimal settleTotal;
    /**
     * 结算返还
     */
    private BigDecimal settleBackTotal;
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
