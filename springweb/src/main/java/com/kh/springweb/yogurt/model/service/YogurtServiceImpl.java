package com.kh.springweb.yogurt.model.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kh.springweb.yogurt.model.entity.YogurtEntity;
import com.kh.springweb.yogurt.model.repository.YogurtRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YogurtServiceImpl implements YogurtService {

	private final YogurtRepository yogurtRepository;
	
	@Override
	public YogurtEntity createEntity(YogurtEntity yogurt) {
		return yogurtRepository.save(yogurt);
	}

	@Override
	public List<YogurtEntity> getYourtEntities() {
		return yogurtRepository.findAll(Sort.by("yogurtId").descending()); // 정렬 기준 // 내림차순은 : Sort.Direction.DESC // .descending()
	}

	@Override
	public YogurtEntity findByid(Long id) {
		return yogurtRepository.findById(id).orElseThrow(() -> new RuntimeException("조회결과 없음 !~"));
	}
	/*
	return yogurtRepository.findById(id);
	옵셔널 조회
	pk가 있는 행이 있다 하면, 반환은 객체를 반환 하는데 
	pk가 없으면, select 했더니 조회 결과가 Null -> 그래서 유연성 있게 처리 하기 위한 오셥널 체이닝 때 orElseThrow를 사용
	
	즉, 조회 결과 있음 entity 없으면 orElseThrow 예외 발생 (옵셔널 클래스를 이용)
	*
	*/
	
	@Override
	public void deleteById(YogurtEntity entity) {
		yogurtRepository.deleteById(entity.getYogurtId());
	}
}
