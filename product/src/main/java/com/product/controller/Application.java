package com.product.controller;

import com.product.service.Register;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Properties;

public class Application{
        public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
        }

        @RequestMapping(value = "/product/id",method = RequestMethod.GET)
        public String  get(HttpServletRequest request){
            return "访问 product  服务端口："+ request.getLocalPort();
        }

        @Bean
        public Register register(){
            Register register = new Register();
            register.register(getAddressAndPort());
            return register;
        }


        private String getAddressAndPort(){
            try {
                Properties properties = new Properties();
                InputStream stream = Application.class.getClassLoader().getResourceAsStream("application.properties");
                properties.load(stream);

                Object port = properties.get("server.port");
                return "127.0.0.1:" + port;
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
}
