package com.jw.elephant.capcplus;

import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.service.CverificationService;
import com.jw.elephant.capcplus.service.UserService;
import com.jw.elephant.capcplus.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
@ComponentScan("com.jw.elephant.capcplus")
@MapperScan("com.jw.elephant.capcplus.mapper")
public class DemoTest {
    @Resource
    private CverificationService cverificationService;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void moreTable(){
        DataTableParam tableParam = new DataTableParam();
        tableParam.setStart(0);
        tableParam.setLength(10);
        tableParam.setDraw(1);
        cverificationService.query(tableParam,null);
    }
    @Test
    public void del(){
        String appIds = "2";
        cverificationService.removeBatchByIds(Arrays.asList(appIds.split(",")));
    }
    @Test
    public void redis(){
        System.out.println(redisTemplate.opsForValue().get("testString"));
        Object cverification = redisUtil.get("cverification");
        System.out.println(cverification);
    }
}
