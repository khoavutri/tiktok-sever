package com.trungtamjava.tiktok.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponeDto<T> {
	private int status;
	private String msg;
	@JsonInclude(value = Include.NON_NULL)
	private T data;

}
