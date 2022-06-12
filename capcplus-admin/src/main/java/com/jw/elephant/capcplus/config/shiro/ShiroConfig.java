package com.jw.elephant.capcplus.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;

/**
 * shiro配置
 * @author zhangjie
 */
@Configuration
public class ShiroConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilterFactoryBean"));
        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * 设置登录认证授权realm
     * @return
     */
    @Bean
    public CapcPlusRealm realm(){
        return new CapcPlusRealm();
    }
    @Bean
    public EmailRealm emailRealm(){
        return new EmailRealm();
    }
    @Bean
    public DefaultWebSecurityManager webSecurityManager(CapcPlusRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //采用MD5算法，使用扰动5次进行
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(5);
        realm.setCredentialsMatcher(matcher);
       /* securityManager.setRealms(Arrays.asList(realm,emailRealm)); ,EmailRealm emailRealm*/
        securityManager.setRealm(realm);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setSuccessUrl("/capcplus/go/index");
        /*shiroFilterFactoryBean.setUnauthorizedUrl("/refuse.jsp");*/
        // 注意过滤器配置顺序 不能颠倒
        HashMap<String, String> chainDefinitions = new HashMap<>(16);
        //静态数据
        chainDefinitions.put("/resources/**","anon");
        chainDefinitions.put("/static/**","anon");
        //文件上传接口
        chainDefinitions.put("/file-img-upload","anon");
        chainDefinitions.put("/file-upload","anon");
        //不需要权限可进的页面（首页不用配）
        chainDefinitions.put("/admin/**","anon");
        chainDefinitions.put("/img/**","anon");
        chainDefinitions.put("/cverify/go/detail","anon");
        chainDefinitions.put("/api/doc/go/detail","anon");
        chainDefinitions.put("/register/go/register","anon");
        chainDefinitions.put("/admin/go/find-account","anon");
        //authc表示需要认证 没有进行身份认证是不能进行访问的
        chainDefinitions.put("/capcplus/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitions);
        return shiroFilterFactoryBean;
    }
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 支持Shiro对Controller的方法级AOP安全控制
     *
     * @DependsOn lifecycleBeanPostProcessor 前置依赖
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(Boolean.TRUE);
        return defaultAdvisorAutoProxyCreator;
    }

}
