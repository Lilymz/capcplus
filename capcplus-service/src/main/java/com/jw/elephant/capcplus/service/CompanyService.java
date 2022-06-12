package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.dto.index.CheckStepDTO;
import com.jw.elephant.capcplus.pojo.Company;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService extends IService<Company> {
    /**
     *  绑定用户企业
     *
     * @param checkStepOneDTO
     * @author zhangjie
     * @date 2022/6/6
     * @return void
     */
    void add(HttpServletRequest request,CheckStepDTO checkStepOneDTO);
}
