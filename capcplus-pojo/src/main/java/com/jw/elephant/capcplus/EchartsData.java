package com.jw.elephant.capcplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * echarts数据渲染
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsData {
    private String name;
    private Long value;
}
