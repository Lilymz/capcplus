package com.jw.elephant.capcplus.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordPayDTO;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordSearchDTO;
import com.jw.elephant.capcplus.mapper.RechargeRecordMapper;
import com.jw.elephant.capcplus.pojo.RechargeRecord;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordOrderStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.RechargeRecordTypeEnum;
import com.jw.elephant.capcplus.service.RechargeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 充值记录service
 * @author zhangjie
 */
@Service
@Slf4j
public class RechargeRecordImpl extends ServiceImpl<RechargeRecordMapper, RechargeRecord> implements RechargeRecordService {
    /**
     *  分页获取充值记录数据
     *
     * @param dataTableParam 分页请求参数
     * @param searchDTO 搜索参数
     * @author zhangjie
     * @date 2022/5/31
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.RechargeRecord>
     */
    @Override
    public PageDT<RechargeRecord> query(DataTableParam dataTableParam, RechargeRecordSearchDTO searchDTO) {
        //构建mybatisPlus所需参数
        Page page = DataTableParam.genPageParam(dataTableParam);
        if (Objects.nonNull(searchDTO)){
            QueryWrapper wrapper = new QueryWrapper();
            if (StringUtils.isNotBlank(searchDTO.getOrderNumber())){
                wrapper.eq("order_number",searchDTO.getOrderNumber());
            }
            if (StringUtils.isNotBlank(searchDTO.getPaySerialNumber())){
                wrapper.eq("pay_serial_number",searchDTO.getPaySerialNumber());
            }
            if (Objects.nonNull(searchDTO.getPayStatus())){
                wrapper.eq("pay_status",searchDTO.getPayStatus());
            }
            if (Objects.nonNull(searchDTO.getPaySerialNumber())){
                wrapper.eq("order_status",searchDTO.getOrderStatus());
            }
            if (Objects.nonNull(searchDTO.getType())){
                wrapper.eq("type",searchDTO.getType());
            }
            Page basePage = page(page, wrapper);
            PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
            return pageDT;
        }
        Page basePage = page(page);
        PageDT pageDT = PageDT.genPage(basePage, dataTableParam.getDraw());
        return pageDT;
    }

    /**
     * 取消支付
     *
     * @param payDTO 支付DTO
     * @author zhangjie
     * @date 2022/5/31
     * @return boolean
     */
    @Override
    public boolean cancelPay(RechargeRecordPayDTO payDTO) {
        Assert.notTrue(payDTO.getType().equals(2),"参数错误，不支持当前取消支付操作");
        //七天内可手动取消支付
        long betweenDay = DateUtil.betweenDay(payDTO.getSubmitTime(), new Date(), true);
        Assert.isTrue(betweenDay<=7,"不允许取消，取消支付超过七天！");
        Assert.isTrue(payDTO.getType().equals(2),"参数错误，不支持当前取消支付操作");
        RechargeRecord updateDO = new RechargeRecord();
        updateDO.setId(payDTO.getId());
        updateDO.setType(RechargeRecordTypeEnum.FAIL);
        updateDO.setOrderStatus(RechargeRecordOrderStatusEnum.CANCEL);
        //取消支付
        return updateById(updateDO);
    }
}
