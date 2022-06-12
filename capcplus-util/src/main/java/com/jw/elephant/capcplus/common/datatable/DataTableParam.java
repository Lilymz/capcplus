package com.jw.elephant.capcplus.common.datatable;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于接收dataTable服务器模式传来的参数
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTableParam{
    /**
     * dataTable所要求，接收在返回
     */
    private Integer draw;

    /**
     * 初始位置
     */
    private Integer start;

    /**
     * 显示长度
     */
    private Integer length;
    /**
     *  解析mybatis-plus所需参数
     * @author zhangjie
     * @date 2022/5/31
     * @return
     */
    public static Page genPageParam(DataTableParam dataTableParam){
        return new Page(dataTableParam.start/dataTableParam.getLength()+1,dataTableParam.getLength());
    }
}
