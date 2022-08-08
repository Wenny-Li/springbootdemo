package com.example.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class MailController {
  @Autowired
  JavaMailSenderImpl javaMailSender;
  @RequestMapping("/mail")
  public String sendMail(){
    String to ="liweiwei.sg@gmail.com";
    String subject ="subject";
    String msg ="email text....";
    final String from ="liweiwei.sg@gmail.com";
    final  String password ="Hanshiyun10";

    Properties props = new Properties();
    props.setProperty("mail.transport.protocol","smtp");
    props.setProperty("mail.host","smtp.gmail.com");
    props.put("mail.smtp.auth","true");
    props.put("mail.smtp.port","465");
    props.put("mail.debug","true");
    props.put("mail.smtp.socketFactory.port","465");
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback","false");
    SimpleMailMessage message = new SimpleMailMessage();
    //邮件发送
    message.setFrom("");
    //邮件接收，多个帐号用逗号分割
    message.setTo("liweiwei.sg@gmail.com");
    //邮件主题
    message.setSubject("公众号：码农洞见");
    //邮件内容
    message.setText("你好，我是码农洞见，欢迎大家关注！");
    javaMailSender.send(message);
    return "邮件发送demo！";
  }
}