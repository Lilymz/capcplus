package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.pojo.SysConfig;

import javax.servlet.http.HttpServletRequest;

public interface SysConfigService extends IService<SysConfig> {
    /**
     *  新增白名单（存在的统一删除重新添加）
     *
     * @param request 当前请求
     * @param whiteList 白名单数据
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    void add(HttpServletRequest request, String whiteList);
}
