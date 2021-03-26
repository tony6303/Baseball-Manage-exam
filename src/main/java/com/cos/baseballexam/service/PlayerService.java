package com.cos.baseballexam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.baseballexam.domain.Player;
import com.cos.baseballexam.domain.PlayerRepository;
import com.cos.baseballexam.domain.Team;
import com.cos.baseballexam.domain.TeamRepository;
import com.cos.baseballexam.web.dto.PlayerSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	
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
}
