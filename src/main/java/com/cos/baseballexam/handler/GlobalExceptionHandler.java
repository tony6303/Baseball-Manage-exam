package com.cos.baseballexam.handler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.baseballexam.web.dto.CMRespDto;

@RestController
@ControllerAdvice // 모든 익셉션을 낚아챔
public class GlobalExceptionHandler {

	// IllegalArgumentException 이 발생하면 실행됨
	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public CMRespDto<?> SQLIntegrityConstraintViolationException(Exception e){
		return new CMRespDto<>(-1, null);
	}
}
