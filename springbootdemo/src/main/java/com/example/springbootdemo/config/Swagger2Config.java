package com.example.springbootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(Environment environment) {

        Profiles profile = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profile);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("DevelopGroup")
                .enable(flag)
                .select()
                //为当前包路径,控制器类包 基于包去扫描
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket app_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("API")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket wap_api() {
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/web/**")).build().groupName("WEB接口文档V4.4").pathMapping("/")
//                .apiInfo(apiInfo());
//    }
//
//    @Bean
//    public Docket web_api() {
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/wap/**")).build().groupName("WAP接口文档V4.4").pathMapping("/")
//                .apiInfo(apiInfo());
//    }
    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Test平台API接口文档")
                .description("description")
                .termsOfServiceUrl("http://terms.com")
                //创建人
                .contact(new Contact("wu", "http://www.java.cc",
                        "test@qq.com"))
                //版本号
                .version("1.0")
                .build();
    }

//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("小K", "https://github.com/kong0827", "1351882069@qq.com");
//        return new ApiInfo("小K的Swagger接口文档",
//                "每天学习一丢丢，每天进步一点点",  // 描述
//                "1.0",
//                "https://github.com/kong0827",
//                contact,                                   // 作者信息
//                "Apache 2.0",
//                "ttps://github.com/kong0827",
//                new ArrayList()
//        );
//    }


}