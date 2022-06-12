package com.jw.elephant.capcplus.common.datatable;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 基于datatable所需结构封装的分页器
 * @author zhangjie
 */
@Data
public class PageDT<T> {
    /**
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是相对应的（Ajax是异步的，因此返回的顺序是不确定的）
     */
    private Integer draw;
    /**
     * 查询总数
     */
    private Integer recordsTotal;
    /**
     * 过滤总数
     */
    private Integer recordsFiltered;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 错误
     */
    private String error;
    /**
     *
     * @param Page<T> mybatis分页累
     * @author zhangjie
     * @date 2022/5/31
     * @return
     */
    public static <T> PageDT<T> genPage(IPage<T> page, Integer draw){
        PageDT<T> PageDT = new PageDT<>();
        PageDT.setData(page.getRecords());
        PageDT.setDraw(draw);
        PageDT.setRecordsFiltered(Integer.valueOf(Long.valueOf(page.getTotal()).toString()));
        PageDT.setRecordsTotal(Integer.valueOf(Long.valueOf(page.getTotal()).toString()));
        return PageDT;
    }
}
