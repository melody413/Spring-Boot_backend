package com.StudentLoginv01.StudentLoginv01.message;

public class ResponseMessage {
	  private String message;
	  private Object data;

	  public ResponseMessage(String message) {
		  this.message = message;
	  }
	  
	  public ResponseMessage(String message, Object data) {
		  this.message = message;
		  this.data = data;
	  }
	
	  public String getMessage() {
	    return message;
	  }
	
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	  public Object getData() {
	    return data;
	  }
		
	  public void setData(Object data) {
	    this.data = data;
	  }
}
