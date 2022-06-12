package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.mapper.AreaMapper;
import com.jw.elephant.capcplus.pojo.Area;
import com.jw.elephant.capcplus.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AreaImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
}
