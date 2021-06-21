package com.bank.app.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {


	@JsonProperty(value = "msg")
	private String message; //  message title

	@JsonProperty(value = "desc")
	private String description; // message description

//	public Error() {
//	}
////
//	
//	public Error(String message, String description) {
//		super();
//		this.message = message;
//		this.description = description;
//	}

//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}


}
