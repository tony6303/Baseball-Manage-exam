package com.cos.baseballexam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.baseballexam.domain.Ground;
import com.cos.baseballexam.service.GroundService;
import com.cos.baseballexam.util.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GroundController{
	private final GroundService groundService;

	@PostMapping("/ground")
	public @ResponseBody String createGround(Ground ground) {
		groundService.야구장생성(ground);
		return Script.href("야구장 생성 성공", "/");
	}
	
	@DeleteMapping("/ground/{id}")
	public @ResponseBody String deleteGround(@PathVariable int id) {
		groundService.야구장삭제(id);
		return "ok";
	}
}
