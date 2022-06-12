package com.jw.elephant.capcplus.dto.server;

import com.jw.elephant.capcplus.constant.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 新增修改统一认证DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CverificationDTO {

    private String id;

    private String companyId;

    private List<String> systemYwIds;

    private String appName;

    private String appLogoUrl;

    private String appDescription;

    private String appPlatform;

    private String appSign;

    private String appPackage;

    private String appLoginClazz;
    public static void check(CverificationDTO cverificationDTO){
        Assert.isBlank(cverificationDTO.getCompanyId(),"该应用没有对应归属！");
        Assert.isTrue(CollectionUtils.isEmpty(cverificationDTO.getSystemYwIds()),"请至少给该应用绑定一个服务分类！");
        Assert.isBlank(cverificationDTO.getAppName(),"请填写应用名称！");
        Assert.isBlank(cverificationDTO.getAppLogoUrl(),"请填写应用logo！");
        Assert.isBlank(cverificationDTO.getAppDescription(),"请填写应用简介！");
        Assert.isBlank(cverificationDTO.getAppPlatform(),"请填写应用平台！");
        Assert.isBlank(cverificationDTO.getAppSign(),"请填写应用签名！");
        Assert.isBlank(cverificationDTO.getAppPackage(),"请填写应用包名！");
        Assert.isBlank(cverificationDTO.getAppLoginClazz(),"请填写应用登录类名！");
    }
}
