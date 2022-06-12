package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.mapper.SysConfigMapper;
import com.jw.elephant.capcplus.pojo.SysConfig;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.SysConfigAscriptionTypeEnum;
import com.jw.elephant.capcplus.pojo.enums.SysConfigTypeEnum;
import com.jw.elephant.capcplus.service.SysConfigService;
import com.jw.elephant.capcplus.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统配置服务
 * @author zhangjie
 */
@Service
@Slf4j
public class SysConfigImpl extends ServiceImpl<SysConfigMapper, SysConfig>  implements SysConfigService {

    /**
     *  新增白名单（存在的统一删除重新添加）
     *
     * @param request 当前请求
     * @param whiteList 白名单数据
     * @author zhangjie
     * @date 2022/6/7
     * @return void
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void add(HttpServletRequest request, String whiteList) {
        User user = (User)request.getSession().getAttribute(TextConstant.USER);
        Assert.isNull(user,"当前未登录，无法访问该接口！");
        //删除当前用户存在的ip
        remove(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getType, SysConfigTypeEnum.WHITE_LIST)
                .eq(SysConfig::getAscriptionType, SysConfigAscriptionTypeEnum.USER)
                .eq(SysConfig::getAscriptionId, user.getId()));
        if (StringUtils.isNotBlank(whiteList)){
            whiteList = whiteList.replaceAll("，", ",");
            List<String> whiteListIP = Arrays.stream(whiteList.split(",")).collect(Collectors.toList());
            List<SysConfig> configs = new ArrayList<>(8);
            // 添加
            for (String  ip: whiteListIP) {
                // ip格式校验
                Assert.notTrue(CommonUtil.RegexValidatorUtil.isIPAddr(ip),"当前ip："+ip+"是非法ip，请核对！");
                SysConfig config = new SysConfig();
                config.setType(SysConfigTypeEnum.WHITE_LIST);
                config.setAscriptionId(user.getId());
                config.setAscriptionType(SysConfigAscriptionTypeEnum.USER);
                config.setCreateTime(new Date());
                config.setName("ip");
                config.setValue(ip);
                configs.add(config);
            }
            saveBatch(configs);
        }
    }
}
