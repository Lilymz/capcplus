package com.jw.elephant.capcplus.vo.server.detail;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jw.elephant.capcplus.pojo.ServerDetail;
import com.jw.elephant.capcplus.pojo.enums.ServerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据详情vo
 * @author zhangjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerDetailVo extends ServerDetail {
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 服务类型
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ServerTypeEnum serverType;

    public ServerDetailVo(ServerDetail serverDetail) {
        super(serverDetail.getId(),serverDetail.getServerId(),serverDetail.getAppId(),serverDetail.getSerialNumber()
                ,serverDetail.getTargetPhone(),serverDetail.getCost(),serverDetail.getResultType(),serverDetail.getResult()
                ,serverDetail.getSupplierType(),serverDetail.getRequestTime(),serverDetail.getCreateTime(),serverDetail.getUpdateTime()
                ,serverDetail.getIsDel());
    }
}
