package com.cos.baseballexam.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.baseballexam.domain.Player;
import com.cos.baseballexam.domain.PlayerRepository;
import com.cos.baseballexam.domain.Team;
import com.cos.baseballexam.domain.TeamRepository;
import com.cos.baseballexam.web.dto.PlayerPositionRespDto;
import com.cos.baseballexam.web.dto.PlayerSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final EntityManager entityManager;
	
	public List<Player> 선수조회() {
		return playerRepository.findAll();
	}

	public @ResponseBody String 선수생성(PlayerSaveReqDto playerSaveReqDto) {
		Team teamEntity = teamRepository.findById(playerSaveReqDto.getTeamId()).get();
		
		Player player = Player.builder()
				.playerName(playerSaveReqDto.getPlayerName())
				.position(playerSaveReqDto.getPosition())
				.team(teamEntity)
				.build();
		
		playerRepository.save(player);
		
		return "index";
	}
	
	public void 선수삭제(int id) {
		playerRepository.deleteById(id);
		
	}
	
	public List<PlayerPositionRespDto> 포지션별선수조회() {
		// 언제 버퍼? - 여러명이서 쓸 때
		// 언제 빌더? - 혼자 쓸 때
		StringBuffer sb = new StringBuffer();
		// 맨뒤에 공백넣는 습관 좋음.
		sb.append("select position, ");
		sb.append("MAX(if(teamId =1, playerName, \"\")) Lotte, ");
		sb.append("MAX(if(teamId =3, playerName, \"\")) Samsung, ");
		sb.append("MAX(if(teamId =5, playerName, \"\")) Deagu ");
		sb.append("from player ");
		sb.append("group by position ");
		
		Query q = entityManager.createNativeQuery(sb.toString());
		
		// QLRM ... 서버 껏다켜서 maven update 하세요
		JpaResultMapper result = new JpaResultMapper();
		List<PlayerPositionRespDto> playerPositionRespDto = result.list(q, PlayerPositionRespDto.class);
		return playerPositionRespDto;
	}
}
