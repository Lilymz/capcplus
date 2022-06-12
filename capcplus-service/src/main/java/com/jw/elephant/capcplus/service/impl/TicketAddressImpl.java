package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.address.AddressDTO;
import com.jw.elephant.capcplus.mapper.TicketAddressMapper;
import com.jw.elephant.capcplus.pojo.*;
import com.jw.elephant.capcplus.service.AreaService;
import com.jw.elephant.capcplus.service.CityService;
import com.jw.elephant.capcplus.service.ProvinceService;
import com.jw.elephant.capcplus.service.TicketAddressService;
import com.jw.elephant.capcplus.vo.ticket.TicketAddressVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 地址 service
 * @author zhangjie
 */
@Service
@Slf4j
public class TicketAddressImpl extends ServiceImpl<TicketAddressMapper, TicketAddress> implements TicketAddressService {
    @Resource
    private ProvinceService provinceService;
    @Resource
    private CityService cityService;
    @Resource
    private AreaService areaService;
    /**
     *  地址新增或更新
     * @param request 请求
     * @param addressDTO 响应
     * @author zhangjie
     * @return void
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveOrUpdate(HttpServletRequest request, AddressDTO addressDTO) {
        AddressDTO.check(addressDTO);
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        TicketAddress addressDO = new TicketAddress();
        BeanUtils.copyProperties(addressDTO,addressDO);
        addressDO.setId(StringUtils.isBlank(addressDTO.getId())?null:Long.valueOf(addressDTO.getId()));
        if (StringUtils.isBlank(addressDTO.getUserId())){
            addressDO.setCreateTime(new Date());
            addressDO.setUserId(Long.valueOf(user.getId()));
        }
        saveOrUpdate(addressDO);
    }
    /**
     * 获取地址列表数据
     *
     * @param dataTableParam 分页参数
     * @author zhangjie
     * @date 2022/6/9
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.TicketAddress>
     */
    @Override
    public PageDT<TicketAddressVo> query(DataTableParam dataTableParam,HttpServletRequest request) {
        Page page = DataTableParam.genPageParam(dataTableParam);
        User user = (User) request.getSession().getAttribute(TextConstant.USER);
        LambdaQueryWrapper<TicketAddress> queryWrapper = new LambdaQueryWrapper<TicketAddress>()
                .eq(TicketAddress::getUserId, user.getId());
        Page<TicketAddress> basePage = page(page,queryWrapper);
        Page<TicketAddressVo> voIPage = (Page<TicketAddressVo>)basePage.convert(TicketAddressVo::new);
        attachAttr(voIPage);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }

    /**
     * vo赋值
     * @param voIPage
     */
    private void attachAttr(Page<TicketAddressVo> voIPage) {
        if (CollectionUtils.isEmpty(voIPage.getRecords())){
            return;
        }
        // 获取省市区ids
        Set<Long> provinceIds = voIPage.getRecords().stream().map(TicketAddressVo::getProvinceId).collect(Collectors.toSet());
        Set<Long> cityIds = voIPage.getRecords().stream().map(TicketAddressVo::getCityId).collect(Collectors.toSet());
        Set<Long> areaIds = voIPage.getRecords().stream().map(TicketAddressVo::getAreaId).collect(Collectors.toSet());
        List<Province> provinces = provinceService.listByIds(provinceIds);
        List<City> cities = cityService.listByIds(cityIds);
        List<Area> areas = areaService.listByIds(areaIds);
        Map<Long, String> provinceMap = provinces.stream().collect(Collectors.toMap(Province::getId,
                Province::getName, (k1, k2) -> k2));
        Map<Long, String> cityMap = cities.stream().collect(Collectors.toMap(City::getId,
                City::getName, (k1, k2) -> k2));
        Map<Long, String> areaMap = areas.stream().collect(Collectors.toMap(Area::getId,
                Area::getName, (k1, k2) -> k2));
        for (TicketAddressVo vo : voIPage.getRecords()) {
            vo.setProvinceStr(StringUtils.defaultIfBlank(provinceMap.get(vo.getProvinceId()),""));
            vo.setCityStr(StringUtils.defaultIfBlank(cityMap.get(vo.getCityId()),""));
            vo.setAreaStr(StringUtils.defaultIfBlank(areaMap.get(vo.getAreaId()),""));
        }
    }
}
