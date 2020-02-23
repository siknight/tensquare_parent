package com.tensquare.sms.rabbitmq;

import com.tensquare.sms.util.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信监听类
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private MailService mailService;

    @RabbitHandler
    public void sendSms(Map<String,String> message){
        System.out.println("手机号："+message.get("mobile"));
        System.out.println("验证码："+message.get("code"));

        mailService.sendSimpleMail(message.get("mobile"),"校园社交验证码","尊敬的用户您好,你的验证码为"+message.get("code")+",五分钟内有效");

    }
}
