package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.ServerStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 服务
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 服务类型
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ServerTypeEnum type;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 业务id
     */
    private String ywId;

    /**
     * 业务密码
     */
    private String ywPwd;

    /**
     * 服务状态：开启，暂停，运行中。。。等
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ServerStatusEnum status;

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
