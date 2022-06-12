package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.index.CheckStepDTO;
import com.jw.elephant.capcplus.mapper.CompanyMapper;
import com.jw.elephant.capcplus.pojo.Company;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.CompanyAuditStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.CompanyTypeEnum;
import com.jw.elephant.capcplus.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 企业服务层
 *
 * @author zhangjie
 */
@Service
@Slf4j
public class CompanyImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    /**
     *  绑定用户企业
     *
     * @param checkStepDTO 企业信息
     * @author zhangjie
     * @date 2022/6/6
     * @return void
     */
    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void add(HttpServletRequest request, CheckStepDTO checkStepDTO) {
        CheckStepDTO.check(checkStepDTO);
        CheckStepDTO.checkTwo(checkStepDTO);
        //获取当前用户
        User user = (User)request.getSession().getAttribute(TextConstant.USER);
        Assert.isNull(user,"用户未登录!");
        Company companyDO = new Company();
        BeanUtils.copyProperties(checkStepDTO,companyDO);
        companyDO.setUserId(user.getId());
        companyDO.setCompanyLevel("普通");
        companyDO.setAuditStatus(CompanyAuditStatusEnum.WAIT);
        companyDO.setType(CompanyTypeEnum.OWNER_FIRM);
        companyDO.setCompanyStatus(CompanyStatusEnum.NORMAL);
        companyDO.setCreateTime(new Date());
        save(companyDO);
    }
}
