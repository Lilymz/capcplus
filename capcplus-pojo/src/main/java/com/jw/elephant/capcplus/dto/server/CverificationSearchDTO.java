package com.jw.elephant.capcplus.dto.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一认证搜索条件
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CverificationSearchDTO {
    /**
     * 应用名称
     */
    private String appName;
    /**
     * AppId
     */
    private String appId;
    /**
     * 平台
     */
    private String appPlatform;
    /**
     * 审核状态
     */
    private String auditStatus;
}
