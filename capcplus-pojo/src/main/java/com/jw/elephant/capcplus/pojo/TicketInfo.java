package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoPaperEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoSealTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketInfoTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开票与发票记录
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo {
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
     * 开票信息id
     */
    private Long ticketId;
    /**
     * 发票类别：1.增值税普通发票，2.增值税专用发票
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketInfoTypeEnum ticketType;
    /**
     * 盖章类型：1.财务章，2.合同章，3.公章
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketInfoSealTypeEnum sealType;
    /**
     * 服务ids
     */
    private String serviceIds;
    /**
     * 账单ids
     */
    private String billIds;
    /**
     * 收货地址
     */
    private Long ticketAddressId;
    /**
     * 是否需要纸质对账单：0.不需要，1.需要
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketInfoPaperEnum isPaper;
    /**
     * 审核状态：1.待审核，2.审核成功，3.驳回
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketInfoStatusEnum status;
    /**
     * 发票项目
     */
    private String ticketProject;
    /**
     * 发票金额
     */
    private BigDecimal ticketBalance;
    /**
     * 申请时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date applyTime;
    /**
     * 审核备注
     */
    private String remark;
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
