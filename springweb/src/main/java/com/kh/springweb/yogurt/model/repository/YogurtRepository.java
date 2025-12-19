package com.kh.springweb.yogurt.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springweb.yogurt.model.entity.YogurtEntity;

public interface YogurtRepository extends JpaRepository<YogurtEntity, Long> { // entity, pk
	
	// INSERT
	// Entity save(Entity entity)
	
	// 전체 조회
	// List<Entit> findAll()
	
	// ID로 조회
	// Optional<Entity> findById(Long id)
	
	// 삭제
	// deleteById(Long id)
	
	// 행 개수
	// Long count();
	
}
