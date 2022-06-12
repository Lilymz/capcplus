package com.jw.elephant.capcplus.dto.statistics;

import com.jw.elephant.capcplus.constant.DateConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 数据统计搜索参数
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsSearchDTO {
    /**
     * 服务类型
     */
    private String serverType;
    /**
     * 服务名称
     */
    private List<String> serverName;
    /**
     * 账单日期
     */
    @DateTimeFormat(pattern = DateConstant.YYYY_MM_DD)
    private Date billDate;
}
