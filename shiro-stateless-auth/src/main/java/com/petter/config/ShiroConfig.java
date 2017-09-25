package com.petter.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 第一：SubjectContext在创建的时候，需要关闭session的创建，这个主要是由DefaultWebSubjectFactory的createSubject进行管理。
 * 创建DefaultWebSubjectFactory的继承类StatelessDefaultSubjectFactory关闭Session的创建
 *
 * 第二：需要禁用使用Sessions 作为存储策略的实现，这个主要由securityManager的subjectDao的sessionStorageEvaluator进行管理的。
 * 第三：需要禁用掉会话调度器，这个主要由sessionManager进行管理。
 * @author hongxf
 * @since 2017-02-28 11:19
 */
@Configuration
public class ShiroConfig {


    /**
     * 关闭Session的创建
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        return new StatelessDefaultSubjectFactory();
    }

    /**
     * session管理器
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * 自己定义的realm.
     * @return
     */
    @Bean
    public StatelessAuthorizingRealm statelessRealm(){
        return new StatelessAuthorizingRealm();
    }

    /**
     * 访问控制器.
     * @return
     */
    @Bean
    public StatelessAccessControlFilter statelessAuthFilter(){
        return new StatelessAccessControlFilter();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(statelessRealm());
         /*
         * 禁用使用Sessions 作为存储策略的实现，但它没有完全地禁用Sessions
         * 所以需要配合context.setSessionCreationEnabled(false);
         */
        ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        factoryBean.getFilters().put("statelessAuth", statelessAuthFilter());
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", "statelessAuth");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    /*@Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {

    }*/

}
