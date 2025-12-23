package com.kh.springweb.yogurt.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@RequiredArgsConstructor
public class S3Service {
	
	// 데이터 마이그레이션 작업
	
	private final S3Client s3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.region.static}")
	private String region;
	
	
	// 업로드 메소드 (파일 저장 시 생기는 url을 db에 저장 > string 타입으로 반환
	public String fileSave(MultipartFile file) {
		
		
		// 파일 이름 바꿔주기 중복 x
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		
		
		// S3에 업로드
		PutObjectRequest request = PutObjectRequest.builder()
													.bucket(bucketName)
													.key(fileName)
													.contentType(file.getContentType())
													.build();
		
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (S3Exception e) {
			e.printStackTrace(); // 나중에 각자 throw를 넣어서 예외 발생 처리 하기
		} catch (AwsServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filePath = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
		
		
		
		
		return filePath;
	}


	public void deleteFile(String filePath) {
		// https://butcket-name.s3.region.amazonaws.com/넘겨받을filePath
		String objectKey = getObjectKeyFromUrl(filePath);
		
		try {
			DeleteObjectRequest request = DeleteObjectRequest.builder()
					.bucket(bucketName)
					.key(objectKey)
					.build();
			s3Client.deleteObject(request);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("S3파일 삭제 실패 : " + e.getMessage());
		}
		
		
		
	}
	
	private String getObjectKeyFromUrl(String filePath) {
		
		if(filePath == null || filePath.isEmpty()) {
			return null;
		}
		
		try {
			URL url = new URL(filePath);
			String path = url.getPath();
			
			System.out.println(path);
			
			return path.substring(1); // index 1번 떼버리자
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("파일 형식이 올바르지 않습니다.");
	}
	
}
