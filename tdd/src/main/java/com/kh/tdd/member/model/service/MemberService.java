package com.kh.tdd.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.tdd.member.model.MemberRepository;
import com.kh.tdd.member.model.dto.MemberTdd;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 아이디로 회원 조회하는 메소드
	public MemberTdd findById(String userId) {
		//MemberTdd member = new MemberTdd();
		//member.setUsreId("user01");
		validate(userId);
		
		return memberRepository.findById(userId)
								.orElseThrow(() -> new RuntimeException(userId));
	}
	
	
	private void validate(String userId) {
		if(userId == null || "".equals(userId)) {
			throw new RuntimeException();
		}
	}
	
	public String login(String userId, String password) {
		
		// 1. 아이디가 DB에 있는 값이어야함 
		//MemberTdd member = findById(userId);
		
		// 2. 비밀번호 검증 로직 ~ // 시큐리티 할 경우 적용
//		if(!encoder.matches(password, member.getPwd())) {
//			throw new 다르다달라();
//		}
		
		return "토큰값";
	}
	
	
}
