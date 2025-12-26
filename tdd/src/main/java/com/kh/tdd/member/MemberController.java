package com.kh.tdd.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.tdd.member.model.dto.MemberTdd;
import com.kh.tdd.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/api/members")
	public ResponseEntity<MemberTdd> getMember(@RequestParam(name="userId") String userId) {
		return ResponseEntity.ok(new MemberTdd("user01"));
	}
		
}
