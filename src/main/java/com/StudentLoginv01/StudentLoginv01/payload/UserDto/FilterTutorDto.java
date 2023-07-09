package com.StudentLoginv01.StudentLoginv01.payload.UserDto;

public class FilterTutorDto {
	private String name;
	private String country;
	private String language;
	private Number startPrice;
	private Number endPrice;
  
	public String getName() {
    	return name;
    }	

    public void setName(String name) {
    	this.name = name;
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
    
    public Number getStartPrice() {
    	return startPrice;
    }	

    public void setStartPrice(Number startPrice) {
    	this.startPrice = startPrice;
    }
    
    public Number getEndPrice() {
    	return endPrice;
    }	

    public void setEndPrice(Number endPrice) {
    	this.endPrice = endPrice;
    }
}
