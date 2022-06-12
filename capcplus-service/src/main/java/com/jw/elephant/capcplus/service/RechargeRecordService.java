package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordPayDTO;
import com.jw.elephant.capcplus.dto.recharge.RechargeRecordSearchDTO;
import com.jw.elephant.capcplus.pojo.RechargeRecord;

/**
 * 充值记录service interface
 * @author zhangjie
 */
public interface RechargeRecordService extends IService<RechargeRecord> {
    /**
     *  分页获取充值记录数据
     *
     * @param dataTableParam 分页请求参数
     * @param searchDTO 搜索参数
     * @author zhangjie
     * @date 2022/5/31
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.RechargeRecord>
     */
    PageDT<RechargeRecord> query(DataTableParam dataTableParam, RechargeRecordSearchDTO searchDTO);

    /**
     * 取消支付
     *
     * @param payDTO 支付DTO
     * @author zhangjie
     * @date 2022/5/31
     * @return boolean
     */
    boolean cancelPay(RechargeRecordPayDTO payDTO);
}
