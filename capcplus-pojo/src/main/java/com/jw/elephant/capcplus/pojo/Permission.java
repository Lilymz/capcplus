package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 权限
 *
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    /***
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /***
     *权限名称
     *
     */
    private String name;

    /***
     *父级id
     *
     */
    private Long parentId;

    /***
     * 权限类型 ：0：功能权限，1：操作权限
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private PermissionEnum type;

    /***
     * 权限字符串
     */
    private String permission;

    /***
     * 权重
     *
     */
    private Integer weight;

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
