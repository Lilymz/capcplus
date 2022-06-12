package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.server.CverificationDTO;
import com.jw.elephant.capcplus.dto.server.CverificationSearchDTO;
import com.jw.elephant.capcplus.pojo.CverificationAppInfo;
import com.jw.elephant.capcplus.vo.cverification.CverificationAppInfoVo;

import javax.servlet.http.HttpServletRequest;

public interface CverificationService extends IService<CverificationAppInfo> {
    /**
     * 分页获取充值记录数据
     * @param dataTableParam 分页参数
     * @param searchDTO 搜索参数
     * @author zhangjie
     * @date 2022/6/6
     * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.Server>
     */
    PageDT<CverificationAppInfoVo> query(DataTableParam dataTableParam, CverificationSearchDTO searchDTO);

    /**
     * 新增应用
     * @param request 请求
     * @param cverificationDTO 新增DTO
     */
    void add(HttpServletRequest request, CverificationDTO cverificationDTO);
    /**
     * 修改应用
     * @param request 请求
     * @param cverificationDTO 修改DTO
     */
    void modify(HttpServletRequest request, CverificationDTO cverificationDTO);
}
