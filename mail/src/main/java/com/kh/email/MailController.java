package com.kh.email;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class MailController {
	private JavaMailSender sender;
	
	@Autowired
	public MailController(JavaMailSender sender) {
		this.sender = sender;
	}
	
	@GetMapping("send")
	public String sendEmail(@RequestBody Map<String, String> mail) {
		
		// 제목, 내용, 받는사람 주소, 참조, 숨은참조
		
		// System.out.println(mail);
		/**
		// 메시지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 제목 작성하기
		message.setSubject("행운의 편지");
		String content = """
				이 편지는 영국에서 최초로 시작되어 일년에 한바퀴를 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진
				이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다.
				복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다.
				""";
		
		// 본문 작성하기
		message.setText(content);
		
		String[] mails=
		{"yoo31318@gmail.com", "yakgom123@gmail.com", "rkdgustjd2756@gmail.com"};

		// 받는이
		message.setTo(mails);
		// 보내기 버튼
		sender.send(message);
		**/		
				// Multipurpose Internet Mail Extensions
		MimeMessage message = sender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8"); // 파일 전송 안하니 false
			
			helper.setSubject("[비밀번호 변경 안내] 까먹으심?");
			
			String tmpPassword = "123";
			
			helper.setText(
				    "<a href='http://localhost:5173/code=123' style='color:red;'>누르세요</a>",
				    true
				);
			
			String[] to = {"yoo31318@gmail.com", "yakgom123@gmail.com", "rkdgustjd2756@gmail.com", "elina001@knou.ac.kr"};
			
			helper.setTo(to);
			
			sender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		
		
		
		return mail.get("to") + "님에게 메일 전송 성공 ~!";
	}
}
