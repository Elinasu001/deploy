package com.kh.tdd.member.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor   // builder 사용하려면 필요
@Builder
@Entity
public class MemberTdd {
	
	@Id
	private String userId;
	
}
