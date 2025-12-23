package com.kh.springweb.yogurt.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity 클래스 - DB테이블하고 연결되는 객체 // Getter / Setter 없으니 @Data 추가 // 테이블을 원래 만들어야 했음

@Entity
@Data
//@Table(name="YOGURT") // 달아줘야 이걸러로 들어간다 없으면? 테이블을 만들어서 데이터가 들어간다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YogurtEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // INSERT 할때마다 자동화 시켜주는 시퀀스
	private Long yogurtId;
	
	@Column // (name="BOARD_WRITER") : 네임 속성이 컬럼명과 관계 없으면 nmae을 써주면 된다. 	// CamelCase로 알아서 바꿔준다.
	private String yogurtName;
	
	@Column
	private String riceName;
	
	@Column
	private String filePath;
	
}
