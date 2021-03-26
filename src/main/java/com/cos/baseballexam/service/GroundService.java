package com.cos.baseballexam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.baseballexam.domain.Ground;
import com.cos.baseballexam.domain.GroundRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GroundService {
	private final GroundRepository groundRepository;
	
	public List<Ground> 야구장조회() {
		return groundRepository.findAll();
	}
	
	public void 야구장생성(Ground ground) {
		groundRepository.save(ground);
	}

	public void 야구장삭제(int id) {
		groundRepository.deleteById(id);
		
	}
}
