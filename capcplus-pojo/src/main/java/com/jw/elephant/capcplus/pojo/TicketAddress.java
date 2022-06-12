package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.TicketAddressDefaultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  地址信息
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketAddress {
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
     * 收货人名称
     */
    private String name;
    /**
     * 省份id
     */
    private Long provinceId;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 区域id
     */
    private Long areaId;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 座机号
     */
    private String telephone;
    /**
     * 默认地址：1。默认
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private TicketAddressDefaultEnum isDefault;
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
