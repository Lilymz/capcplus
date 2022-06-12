package com.jw.elephant.capcplus.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jw.elephant.capcplus.constant.DateConstant;
import com.jw.elephant.capcplus.pojo.enums.MessageStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消息pojo
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 收信人
     */
    private Long userId;
    /**
     * 消息类型：1.服务消息，2.系统消息，3.余额预警，4.资料修改
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private MessageTypeEnum type;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 状态：0：未读，1：已读
     *
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private MessageStatusEnum status;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 日期（创建时间）
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;
    /**
     *更新时间
     */
    @JSONField(format = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date updateTime;

    @TableLogic
    private Integer isDel;
}
