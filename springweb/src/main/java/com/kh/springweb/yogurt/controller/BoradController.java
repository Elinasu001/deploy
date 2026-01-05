package com.kh.springweb.yogurt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/boards")
@Tag(name = "Boards API")
public class BoradController {
	
	
	@Operation(summary = "보드조회" , description= "전달값 없이 전체 조회")
	@GetMapping
	public String getBoards() {
		return "보드당";
	}
}
