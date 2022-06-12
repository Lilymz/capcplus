package com.jw.elephant.capcplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jw.elephant.capcplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author zhangjie
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据账号获取用户
     * @param param
     * @return
     */
    User find(@Param("param") Object param);
}
