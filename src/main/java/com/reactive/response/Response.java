package com.reactive.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

	private Integer statusCode;
	private String statusMessage;
}
