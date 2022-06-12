package com.jw.elephant.capcplus.vo.cverification;

import com.jw.elephant.capcplus.pojo.CverificationAppInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统一认证vo
 * @author zhangjie
 */
@Data
@NoArgsConstructor
public class CverificationAppInfoVo extends CverificationAppInfo {

    private List<String> systemYwNames;

    public CverificationAppInfoVo(CverificationAppInfo DO) {
        this.setId(DO.getId());
        this.setUserId(DO.getUserId());
        this.setAppPlatform(DO.getAppPlatform());
        this.setAppName(DO.getAppName());
        this.setAppId(DO.getAppId());
        this.setAppPackage(DO.getAppPackage());
        this.setAppLoginClazz(DO.getAppLoginClazz());
        this.setAppSign(DO.getAppSign());
        this.setAppLogoUrl(DO.getAppLogoUrl());
        this.setAppDescription(DO.getAppDescription());
        this.setSystemYwIds(DO.getSystemYwIds());
        this.setAppStatus(DO.getAppStatus());
        this.setAuditStatus(DO.getAuditStatus());
        this.setAuditRemark(DO.getAuditRemark());
        this.setCurStatus(DO.getCurStatus());
        this.setCreateTime(DO.getCreateTime());
        this.setUpdateTime(DO.getUpdateTime());
        this.setIsDel(DO.getIsDel());
    }
}
