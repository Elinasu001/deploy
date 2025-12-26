package com.kh.tdd.member.model;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.tdd.member.model.dto.MemberTdd;

public interface MemberRepository extends JpaRepository<MemberTdd, String> {

	
	
}
