package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.EmailNotification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{

  private JavaMailSender javaMailSender;
  @Override
  public String send(EmailNotification email) {
    if(email.isHasTemplate()){
      sendTemplate(email);
    }else{
      sendPlainText(email);
    }
    return "Your Email has been sent succesfully";
  }

  private void sendPlainText(EmailNotification email){
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(email.getTo());
    mailMessage.setSubject(email.getSubject());
    mailMessage.setText(email.getBody());
    javaMailSender.send(mailMessage);
  }

  private void sendTemplate(EmailNotification email){
    MimeMessage mailMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
    try {
      helper.setTo(email.getTo());
      helper.setSubject(email.getSubject());
      helper.setText(email.getBody(), true);
    }catch(MessagingException e){
      throw new RuntimeException(e);
    }
    javaMailSender.send(mailMessage);

  }

}
