package com.ecommerce.ecomerceapp.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@EnableWebMvc
public class SpringServletConfig {

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/view/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean
    public SessionFactory sessionFactory(){
        AnnotationSessionFactoryBean sessionFactoryBean = new AnnotationSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }
}
