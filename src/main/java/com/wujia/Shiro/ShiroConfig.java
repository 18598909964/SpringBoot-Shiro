package com.wujia.Shiro;
/*
 *shrio配置类
 *
 * */
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*创建 ShiroFilterFactoryBean*/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /**
         * 常用过滤器
         *      anon: 无需认证（登录）可以访问
         *      authc: 必须认证才可访问
         *      user: 如果使用rememberMe的功能可以直接访问
         *      perms: 必须得到资源权限才可已访问
         *      role: 必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //认证过滤器
        System.out.println("认证过滤");
        filterMap.put("/test/hellotest", "anon");
        filterMap.put("/test/helloShiro", "anon");
        //资源授权过滤器

        filterMap.put("/test/*", "authc");     //过滤所有请求

        //未通过认证过滤器的请求跳转路径
        shiroFilterFactoryBean.setLoginUrl("/test/hellotest ");
        //未通过资源授权过滤器的请求跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /*创建DefaultWebSecurityManager*/
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*创建Realm程序*/
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

    //配置ShiroDialect，用于Thymeleaf和Shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
