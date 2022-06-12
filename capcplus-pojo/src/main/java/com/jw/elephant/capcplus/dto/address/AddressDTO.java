package com.jw.elephant.capcplus.dto.address;

import com.jw.elephant.capcplus.constant.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    /**
     * 地址id
     */
    private String id;
    /**
     * 归属人
     */
    private String userId;
    /**
     * 收货人名称
     */
    private String name;
    /**
     * 省份id
     */
    private Long provinceId;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 区域id
     */
    private Long areaId;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 座机号
     */
    private String telephone;

    /**
     * 是否默认地址
     */
    private String isDefault;

    /**
     * 数据校验
     * @param addressDTO 地址DTO
     */
    public static void check(AddressDTO addressDTO){
        Assert.isBlank(addressDTO.getName(),"请填写收货人姓名！");
        Assert.isNull(addressDTO.getProvinceId(),"请选择省份！");
        Assert.isNull(addressDTO.getCityId(),"请选择城市！");
        Assert.isNull(addressDTO.getAreaId(),"请选择区域！");
        Assert.isBlank(addressDTO.getDetailAddress(),"请填写详细地址！");
        Assert.isBlank(addressDTO.getPostCode(),"请填写邮政编码！");
        Assert.isBlank(addressDTO.getPhone(),"请填写手机号！");
        Assert.isBlank(addressDTO.getIsDefault(),"请选择是否默认地址！");
    }
}
