package com.bank.app.security;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthentictRequest {
	
	@NotBlank(message = "UserName is empty")
	@NotNull
	private String userName;
	@NotBlank(message = "Password is empty")
	@NotNull
	private String password;
	

}
