package com.cos.baseballexam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.baseballexam.service.TeamService;
import com.cos.baseballexam.util.Script;
import com.cos.baseballexam.web.dto.TeamSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TeamController {

	private final TeamService teamService;
	
	@PostMapping("/team")
	public @ResponseBody String createTeam(TeamSaveReqDto teamSaveReqDto) {
		teamService.팀생성(teamSaveReqDto);
		
		return Script.href("팀 생성 성공", "/");
	}
	
	@DeleteMapping("/team/{id}")
	public @ResponseBody String deleteTeam(@PathVariable int id) {
		teamService.팀삭제(id);
		return "ok";
	}
	
}
