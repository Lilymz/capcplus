package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.SysConfigAscriptionTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.SysConfigTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统配置pojo
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig {
    /**
     *  id
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 场景类型：1：首页，2.应用场景，3.产品服务，4.接口api
     *
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private SysConfigTypeEnum type;

    /**
     * 配置归属类型：0：系统，：用户，2：管理员
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private SysConfigAscriptionTypeEnum ascriptionType;

    /**
     * 归属id
     */
    private Long ascriptionId;

    /**
     * 属性名
     *
     */
    private String name;
    /**
     * 属性值
     *
     */
    private String value;
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
