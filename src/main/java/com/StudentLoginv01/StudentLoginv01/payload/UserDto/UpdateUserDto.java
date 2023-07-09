package com.StudentLoginv01.StudentLoginv01.payload.UserDto;

public class UpdateUserDto {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String image;
    public String certification;
    public String headline;
    public String description;
    public String country;
    public String language;
    public Integer rate;
    public String role;
    
    public String getName() {
    	return name;
    }	

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getSurname() {
    	return surname;
    }	

    public void setSurname(String surname) {
    	this.surname = surname;
    }
    
    public String getUsername() {
    	return username;
    }	

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getEmail() {
    	return email;
    }	

    public void setEmail(String email) {
    	this.email = email;
    }
	
	public String getImage() {
    	return image;
    }	

    public void setImage(String image) {
    	this.image = image;
    }
    

    
    public String getCertification() {
  	  return certification;
    }
    
    public void setCertification(String certification) {
  	  this.certification = certification;
    }
    
    public String getCountry() {
  	  return country;
    }
    
    public void setCountry(String country) {
  	  this.country = country;
    }
    
    public String getLanguage() {
  	  return language;
    }
    
    public void setLanguage(String language) {
  	  this.language = language;
    }
    
    public String getHeadline() {
  	  return headline;
    }
    
    public void setHeadline(String headline) {
  	  this.headline = headline;
    }
    
    public String getDescription() {
  	  return description;
    }
    
    public void setDescription(String description) {
  	  this.description = description;
    }

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    public Integer getRate() {
      return rate;
    }

    public void setRate(Integer rate) {
      this.rate = rate;
    }
}
