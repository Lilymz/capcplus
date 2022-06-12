package com.jw.elephant.capcplus.dto.server.detail;

import com.jw.elephant.capcplus.constant.DateConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 服务调用详情搜索DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDetailSearchDTO {

    /**
     * 流水号
     */
    private String serialNumber;
    /**
     *  服务类型
     */
    private String serverType;
    /**
     * 服务名称
     */
    private List<String> serverName;
    /**
     * 返回类型
     */
    private String resultType;
    /**
     * 返回值
     */
    private String result;
    /**
     * 供应商类型
     */
    private String supplierType;
    /**
     * 应用名称
     */
    private String appName;
    /**
     *  应用id
     */
    private String appId;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date beginTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = DateConstant.YYYY_MM_DD_HH_MM_SS)
    private Date endTime;
}
