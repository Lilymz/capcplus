package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.mapper.ProvinceMapper;
import com.jw.elephant.capcplus.pojo.Province;
import com.jw.elephant.capcplus.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProvinceImpl extends ServiceImpl<ProvinceMapper,Province> implements ProvinceService {
}
