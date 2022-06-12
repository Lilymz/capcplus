package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.TicketStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.TicketTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 开票信息
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
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
     * 纳税人资格类型：1：小规模纳税人，2.一般纳税人
     *
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketTypeEnum type;
    /**
     * 审核状态 1.待审核，2：审核通过，3：驳回
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketStatusEnum status;
    /**
     * 开票抬头
     */
    private String ticketMan;
    /**
     * 统一社会信用代码/税务登记号
     */
    private String creditCode;
    /**
     * 开户行
     */
    private String bank;
    /**
     * 开户账号
     */
    private String bankAccount;
    /**
     * 营业电话
     */
    private String businessPhone;
    /**
     * 营业地址
     */
    private String businessAddress;
    /**
     * 营业执照/税务登记证扫描件
     */
    private String businessLicense;
    /**
     * 资质（一般纳税人资格认证扫描件）
     */
    private String qualification;
    /**
     * 其他文件
     */
    private String otherFile;
    /**
     * 备注
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
