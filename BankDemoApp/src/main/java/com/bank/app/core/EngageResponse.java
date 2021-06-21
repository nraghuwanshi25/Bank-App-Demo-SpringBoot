package com.bank.app.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngageResponse {

	@JsonProperty("msg")
	private String message;

	private Object data;


	private List<Error> error;


//	public String getMessage() {
//		return message;
//	}
//
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//
//	public Object getData() {
//		return data;
//	}
//
//
//	public void setData(Object data) {
//		this.data = data;
//	}
//
//
//	public List<Error> getError() {
//		return error;
//	}
//
//
//	public void setError(List<Error> error) {
//		this.error = error;
//	}
//	

}
