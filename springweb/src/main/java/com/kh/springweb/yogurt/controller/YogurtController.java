package com.kh.springweb.yogurt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.springweb.yogurt.dto.ResponseData;
import com.kh.springweb.yogurt.model.entity.YogurtEntity;
import com.kh.springweb.yogurt.model.service.S3Service;
import com.kh.springweb.yogurt.model.service.YogurtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/yogurts")
@RequiredArgsConstructor
public class YogurtController {
	
	private final YogurtService yogurtService;
	private final S3Service s3Service;
	
	// INSERT
	// 응답 코드 통일 시키기 ** / 
	@PostMapping										// requestBody에서 requestparam으로 
	public ResponseEntity<ResponseData<YogurtEntity>> createEntity(@RequestParam(name="yogurtName") String yogurtName,
													@RequestParam(name="riceName") String riceName,
													@RequestParam(name="file") MultipartFile file){
		
		
		// 파일 올리기
		// 원래는 if문 써서 파일 이거 해줘야 하는데 서비스에서 해야되니 일단 패스
		// if(!file.getOriginalFilename().equals("")) {
		String fileUrl = s3Service.fileSave(file);
		
		YogurtEntity entity = YogurtEntity.builder()
				  							.yogurtName(yogurtName)
				  							.riceName(riceName)
				  							.filePath(fileUrl)
				  							.build();
		
		// 확인 한 번 하기
		
		YogurtEntity responseYogurt = yogurtService.createEntity(entity);
		
		return ResponseData.ok(responseYogurt, "요거트 등록에 성공");
	}
	
	//SELECT
	@GetMapping
	public ResponseEntity<ResponseData<List<YogurtEntity>>> getEntities(){
		// 페이징 처리 없이 일단, 전체 다 들고 간다고 가정하기
		List<YogurtEntity> yogurts = yogurtService.getYourtEntities();
		/*
		ResponseData rd = ResponseData.builder()
								.data(yogurts)
								.message("전체조회성공")
								.build();
		*/
		//return ResponseEntity.status(HttpStatus.OK).body(rd);
		return ResponseData.ok(yogurts, "전체조회성공");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseData<Object>> deleteById(@PathVariable(name="id")Long id){
		
		// 밑 작업은 모두 서비스에서 
		YogurtEntity entity = yogurtService.findByid(id);
		
		// 원래 서비스단에서 돌려야 된다.
		// 만약에 파일첨부였다면 파일을 S3에서 삭제
		if(entity.getFilePath() != null && !entity.getFilePath().isEmpty()) {
			s3Service.deleteFile(entity.getFilePath());
		}
		
		yogurtService.deleteById(entity);
		
		return ResponseData.ok(null);
	}

}
