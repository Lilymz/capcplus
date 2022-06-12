package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.server.detail.ServerDetailSearchDTO;
import com.jw.elephant.capcplus.pojo.ServerDetail;
import com.jw.elephant.capcplus.vo.server.detail.ServerDetailVo;

public interface ServerDetailService extends IService<ServerDetail> {
    /**
     * 获取数据统计列表
     * @param dataTableParam 分页参数
     * @param searchDTO 搜索参数
     * @return
     */
    PageDT<ServerDetailVo> query(DataTableParam dataTableParam, ServerDetailSearchDTO searchDTO);
}
