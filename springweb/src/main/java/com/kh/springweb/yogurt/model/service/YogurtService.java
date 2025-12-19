package com.kh.springweb.yogurt.model.service;

import java.util.List;

import com.kh.springweb.yogurt.model.entity.YogurtEntity;

public interface YogurtService {
	
	YogurtEntity createEntity(YogurtEntity yogurt);
	
	List<YogurtEntity> getYourtEntities();
	
}
