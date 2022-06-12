package com.jw.elephant.capcplus.dto.index;

import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业申请第一步DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckStepDTO {
    private String name ;
    private String registerCode;
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;
    private String companyAddress;
    private String telPhone;
    private String companyScope;
    private String dueDate;
    private String introduce;
    private String tempPhone;
    private String IdPhoto;
    public static void check(CheckStepDTO checkStepOneDTO){
        Assert.isBlank(checkStepOneDTO.getName(),"名称不能为空！");
        Assert.isBlank(checkStepOneDTO.getRegisterCode(),"注册号不能为空！");
        Assert.isNull(checkStepOneDTO.getProvinceId(),"省份不能为空！");
        Assert.isNull(checkStepOneDTO.getCityId(),"城市不能为空！");
        Assert.isNull(checkStepOneDTO.getAreaId(),"区域不能为空！");
        Assert.isBlank(checkStepOneDTO.getCompanyAddress(),"详细地址不能为空！");
        Assert.isBlank(checkStepOneDTO.getTelPhone(),"固定电话不能为空！");
        Assert.isTrue(checkStepOneDTO.getTelPhone().length()>15,"固定号码长度限制15位！");
        Assert.isBlank(checkStepOneDTO.getCompanyScope(),"经营范围不能为空！");
        Assert.isBlank(checkStepOneDTO.getDueDate(),"经营期限不能为空！");
        Assert.isBlank(checkStepOneDTO.getTempPhone(),"联系人手机号码不能为空！");
        Assert.notTrue(CommonUtil.RegexValidatorUtil.isMobile(checkStepOneDTO.getTempPhone()),"手机号格式错误");
    }
    public static void checkTwo(CheckStepDTO checkStepOneDTO){
        Assert.isBlank(checkStepOneDTO.getIdPhoto(),"证件照不能为空！");
    }
}
