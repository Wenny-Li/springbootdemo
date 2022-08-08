package com.example.springbootdemo.config;

import com.example.springbootdemo.util.YamlConfigurerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(MyMvcConfig.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
    @Bean
    public YamlConfigurerUtil ymlConfigurerUtil() {
        //1:加载配置文件
        Resource app = new ClassPathResource("/application.yaml");
        Resource appDev = new ClassPathResource("/application-dev.yaml");
        Resource appProd = new ClassPathResource("/application-prod.yaml");
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        // 2:将加载的配置文件交给 YamlPropertiesFactoryBean
        yamlPropertiesFactoryBean.setResources(app);
        // 3：将yml转换成 key：val
        Properties properties = yamlPropertiesFactoryBean.getObject();
        String active = properties.getProperty("spring.profiles.active");
        if (active == "" || active == null) {
            logger.error("未找到spring.profiles.active配置！");
        }else {
            //判断当前配置是什么环境
            if ("dev".equals(active)) {
                yamlPropertiesFactoryBean.setResources(app,appDev);
            }else if("prod".equals(active)){
                yamlPropertiesFactoryBean.setResources(app,appProd);
            }
        }
        // 4: 将Properties 通过构造方法交给我们写的工具类
        YamlConfigurerUtil ymlConfigurerUtil = new YamlConfigurerUtil(yamlPropertiesFactoryBean.getObject());
        return ymlConfigurerUtil;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**").excludePathPatterns("/index.html", "/", "/user/login", "/css/*","/img/*");
//    }


}
