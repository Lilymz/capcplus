package com.jw.elephant.capcplus.vo.statistics;

import com.jw.elephant.capcplus.pojo.Statistics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsVo extends Statistics {
    private String serverName;

    public StatisticsVo(Statistics DO) {
        this.setId(DO.getId());
        this.setServerId(DO.getServerId());
        this.setUseTotal(DO.getUseTotal());
        this.setSuccessTotal(DO.getSuccessTotal());
        this.setUnknownTotal(DO.getUnknownTotal());
        this.setFailTotal(DO.getFailTotal());
        this.setCostTotal(DO.getCostTotal());
        this.setDevCostTotal(DO.getDevCostTotal());
        this.setBackTotal(DO.getBackTotal());
        this.setRealTotal(DO.getRealTotal());
        this.setSettleTotal(DO.getSettleTotal());
        this.setSettleBackTotal(DO.getSettleBackTotal());
        this.setCreateTime(DO.getCreateTime());
        this.setUpdateTime(DO.getUpdateTime());
        this.setIsDel(DO.getIsDel());
    }
}
