package com.nzavod.springbooteducation.service;

import com.nzavod.springbooteducation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    MailSender mailSender;
    public void sendMessage(String message, String address){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(message);
        mailMessage.setTo(address);
        mailMessage.setFrom("zavnical@gmail.com");
        mailSender.send(mailMessage);
        System.out.println("письмо ушло");//for debug
    }


}
