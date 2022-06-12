package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.EchartsData;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.statistics.StatisticsSearchDTO;
import com.jw.elephant.capcplus.pojo.Statistics;
import com.jw.elephant.capcplus.vo.statistics.StatisticsVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface StatisticsService extends IService<Statistics> {
    /**
     * 获取数据统计列表
     * @param dataTableParam
     * @param searchDTO
     * @return
     */
    PageDT<StatisticsVo> query(DataTableParam dataTableParam, StatisticsSearchDTO searchDTO);

    List<EchartsData> successStatistics(HttpServletRequest request);
}
