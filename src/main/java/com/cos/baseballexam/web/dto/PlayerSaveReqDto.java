package com.cos.baseballexam.web.dto;

import lombok.Data;

@Data
public class PlayerSaveReqDto {
	private String playerName;
	private String position;
	private int teamId;
}
