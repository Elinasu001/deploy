package com.kh.springweb.yogurt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springweb.yogurt.dto.ResponseData;
import com.kh.springweb.yogurt.model.entity.YogurtEntity;
import com.kh.springweb.yogurt.model.service.YogurtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/yogurt")
@RequiredArgsConstructor
public class YogurtController {
	
	private final YogurtService yogurtService;
	
	// INSERT
	// 응답 코드 통일 시키기 ** / 
	@PostMapping
	public ResponseEntity<ResponseData> createEntity(@RequestBody YogurtEntity yogurt){
		
		YogurtEntity responseYogurt = yogurtService.createEntity(yogurt);
		
		ResponseData rd = ResponseData.builder()
									.message("요거트 등록에 성공") 
									.data(responseYogurt)
									.build();
		
		// 응답 받을 때 어떤 컴포넌트든 앞단 작업 코드도 동일하게 작업 가능
		return ResponseEntity.status(HttpStatus.CREATED).body(rd);
	}
	
	//SELECT
	@GetMapping
	public ResponseEntity<ResponseData> getEntities(){
		// 페이징 처리 없이 일단, 전체 다 들고 간다고 가정하기
		List<YogurtEntity> yogurts = yogurtService.getYourtEntities();
		ResponseData rd = ResponseData.builder()
								.data(yogurts)
								.message("전체조회성공")
								.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(rd);
	}

}
