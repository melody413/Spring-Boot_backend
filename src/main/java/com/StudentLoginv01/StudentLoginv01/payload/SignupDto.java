package com.StudentLoginv01.StudentLoginv01.payload;

import lombok.Data;

@Data
public class SignupDto {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String role;
    private String image;
    private String video;
    private String certification;
    private String country;
    private String language;
    private String headline;
    private String description;
    private Integer rate;
    
    public String getUsername() {
	    return username;
	}
	
    public void setUsername(String username) {
	  this.username = username;
    }
    
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

	public String getRole() {
	    return role;
	}
	
	public void setRoles(String role) {
	    this.role = role;
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

	public String getImage() {
	    return image;
	}
	
	public void setImage(String image) {
	    this.image = image;
	}

	public String getVideo() {
	    return video;
	}
	
	public void setVideo(String video) {
	    this.video = video;
	}

	public String getCertification() {
	    return certification;
	}
	
	public void setCertification(String certification) {
	    this.certification = certification;
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
	
	public Integer getRate() {
		return rate;
	}
	
	public void setRate(Integer rate) {
		this.rate = rate;
	}
}