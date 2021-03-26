package com.cos.baseballexam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.baseballexam.domain.Ground;
import com.cos.baseballexam.domain.GroundRepository;
import com.cos.baseballexam.domain.Team;
import com.cos.baseballexam.domain.TeamRepository;
import com.cos.baseballexam.web.dto.TeamSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeamService {
	private final TeamRepository teamRepository;
	private final GroundRepository groundRepository;
	
	public List<Team> 팀조회() {
		return teamRepository.findAll();
	}
	
	public void 팀생성(TeamSaveReqDto teamSaveReqDto) {
		Ground groundEntity = groundRepository.findById(teamSaveReqDto.getHomeGroundId()).get();
		Team team = Team.builder()
				.teamName(teamSaveReqDto.getTeamName())
				.homeGround(groundEntity)
				.build();
		
		teamRepository.save(team);
	}
	
	public void 팀삭제(int id) {
		teamRepository.deleteById(id);
		
	}

}
