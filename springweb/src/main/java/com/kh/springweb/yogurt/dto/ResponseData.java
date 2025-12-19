package com.kh.springweb.yogurt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseData {
	
	private String message;
	private Object data;
	
}
