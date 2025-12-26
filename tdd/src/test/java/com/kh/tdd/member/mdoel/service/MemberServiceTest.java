package com.kh.tdd.member.mdoel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kh.tdd.member.model.MemberRepository;
import com.kh.tdd.member.model.dto.MemberTdd;
import com.kh.tdd.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
	
	@Mock
	private MemberRepository memberRepository;
	
	@InjectMocks
	private MemberService memberService;
	
	@Test
	@DisplayName("아이디를 전달하면 회원정보를 반환")
	void 사용자_아이디로_조회() {
		
		// 요구사항을 코드로 만든다.
		// userId을 뺴와서 아이디를 조회하여 비교 해봤는데 아이디랑 똑같을 경우, 
		
		// 레드(실패) -> 그린 -> 리팩토링
		// given
		String userId = "user01";
		MemberTdd mockMember = new MemberTdd(userId);
		when(memberRepository.findById(userId)).thenReturn(Optional.of(mockMember));
		
		// when
		MemberTdd member = memberService.findById(userId);
		
		// then
		assertThat(member.getUserId()).isEqualTo(userId);
		
		// 레드 1단계 : findById가 없어서 테스트 실패 -> findById만 구현 후 테스트
		// fidnById 메소드만 구현 시, NullPointExcpetion이 생긴다 : memberService필드지만, 아무것도 없기 때문이다. 
		// 이 경우: @Mock을 생성하여 
		
		// 레드 2단계 : member.GetUserId() 메소드 호출 시 member가 null이라서 NullPointerException -> member를 생성해서 반환
		// 그린 (최소구현)
		
		// 리팩토링
		
		
	}
	
	@Test
	void 로그인_성공() {
		// 이 테스트코드 자체가 명세
		// " 아이디 / 비밀번호를 넣으면 토큰이 발급 "
		
		// given - 입력값
		String userId = "admin";
		String password = "1234";
		
		// when - 어떤 동작
		String jwtToken = memberService.login(userId, password);
		
		// then - 기대 결과
		assertThat(jwtToken).isNotNull(); // 여기까지 작성 시, 값이 없으니 RED 단계
		
	}
}
