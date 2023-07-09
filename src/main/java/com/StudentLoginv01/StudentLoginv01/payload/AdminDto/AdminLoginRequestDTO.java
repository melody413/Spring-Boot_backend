package com.StudentLoginv01.StudentLoginv01.payload.AdminDto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminLoginRequestDTO {
    @Email
    @NotBlank(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;
    @NotBlank(message = "password cannot be empty")
    @NotNull(message = "password cannot be null")
    private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
