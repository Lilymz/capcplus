package com.jw.elephant.capcplus.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * mybatis-plus相关配置
 * @author zhangjie
 */
@Configuration
public class MyBatisPlusConfig {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    /**
     *  sql SessionFactory配置
     * @author zhangjie
     * @date 2022/5/31
     * @return org.apache.ibatis.session.SqlSessionFactory
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus")
    public SqlSessionFactory sqlSessionFactory(MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     *  配置mybatis分页插件
     *
     * @author zhangjie
     * @date 2022/5/31
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.HSQL);
        paginationInnerInterceptor.setOverflow(true);
        paginationInnerInterceptor.setOptimizeJoin(true);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }
}
