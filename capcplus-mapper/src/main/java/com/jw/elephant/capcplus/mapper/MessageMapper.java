package com.jw.elephant.capcplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jw.elephant.capcplus.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
