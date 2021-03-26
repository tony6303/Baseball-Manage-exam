package com.cos.baseballexam.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.baseballexam.domain.Ground;
import com.cos.baseballexam.domain.Player;
import com.cos.baseballexam.domain.Team;
import com.cos.baseballexam.service.GroundService;
import com.cos.baseballexam.service.PlayerService;
import com.cos.baseballexam.service.TeamService;
import com.cos.baseballexam.util.Script;
import com.cos.baseballexam.web.dto.PlayerPositionRespDto;
import com.cos.baseballexam.web.dto.PlayerSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PlayerController {

	private final PlayerService playerService;
	private final GroundService groundService;
	private final TeamService teamService;
	
	@GetMapping("/")
	public String getPlayer(Model model) {
		List<Player> players = playerService.선수조회();
		List<Ground> grounds = groundService.야구장조회();
		List<Team> teams = teamService.팀조회();
		List<PlayerPositionRespDto> dtos = playerService.포지션별선수조회();
		
		model.addAttribute("players",players);
		model.addAttribute("grounds",grounds);
		model.addAttribute("teams",teams);
		model.addAttribute("dtos", dtos);
		
		return "index";
	}
	
	@PostMapping("/player")
	public @ResponseBody String  createPlayer(PlayerSaveReqDto playerSaveReqDto) {
		playerService.선수생성(playerSaveReqDto);
		
		return Script.href("선수 생성 성공", "/");
	}
	
	@DeleteMapping("/player/{id}")
	public @ResponseBody String deletePlayer(@PathVariable int id) {
		playerService.선수삭제(id);
		return "ok";
	}
}
