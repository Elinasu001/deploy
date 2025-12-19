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

}
