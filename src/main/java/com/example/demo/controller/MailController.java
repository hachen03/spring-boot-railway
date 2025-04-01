package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MailService;

@RestController
@RequestMapping("/contact")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/send-email")
	public String sendEmail(@RequestBody Map<String, String> request) { //有標註@RequestBody 時，Spring 框架會自動使用 HttpMessageConverter 來處理請求的 body。其中，MappingJackson2HttpMessageConverter 是一個常見的 JSON 轉換器，它會將接收到的 JSON 字串轉換為 Java 物件。
		String name = request.get("name");
		String email = request.get("email");
        String message = request.get("message");
        
        
        mailService.sendEmail(name, email, message);
        return "郵件已成功發送!";
        		
	}

}
