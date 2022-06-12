package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.mapper.CityMapper;
import com.jw.elephant.capcplus.pojo.City;
import com.jw.elephant.capcplus.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CityImpl extends ServiceImpl<CityMapper, City> implements CityService {
}
