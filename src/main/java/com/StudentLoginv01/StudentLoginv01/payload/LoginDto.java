package com.StudentLoginv01.StudentLoginv01.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
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