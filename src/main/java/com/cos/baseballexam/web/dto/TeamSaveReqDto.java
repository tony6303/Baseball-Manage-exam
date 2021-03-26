package com.cos.baseballexam.web.dto;

import lombok.Data;

@Data
public class TeamSaveReqDto {
	private String teamName;
	private int homeGroundId;
}
