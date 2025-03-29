package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.from}")
	private String fromEmail;
	
	@Value("${mail.to}")
	private String toEmail;
	
	public void sendEmail(String name,String email,String messageContent) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setFrom(fromEmail);
            helper.setTo(toEmail); // 把客戶需求寄到你自己的信箱
            helper.setSubject("客戶需求來自: " + name);
            helper.setText(
                "<p><b>姓名:</b> " + name + "</p>" +
                "<p><b>Email:</b> " + email + "</p>" +
                "<p><b>需求內容:</b></p>" +
                "<p>" + messageContent + "</p>",
                true
            );
            
            mailSender.send(message);
            System.out.println("郵件已成功發送！");
		}catch (MessagingException e) {
			 e.printStackTrace();
		}
	}

}
