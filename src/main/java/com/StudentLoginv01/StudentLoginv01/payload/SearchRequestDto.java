package com.StudentLoginv01.StudentLoginv01.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
	
	String column;
	String value;
	String joinTable;
	Operation operation;
	
	public enum Operation {
		EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN;
	}
	
	public Operation getOperation() {
	    return operation;
	}
	
	public void setOperation(Operation operation) {
	    this.operation = operation;
	}
	
	public String getColumn() {
	    return column;
	}	

    public void setColumn(String column) {
    	this.column = column;
    }
	
	public String getValue() {
	    return value;
	}	

    public void setValue(String value) {
    	this.value = value;
    }
    
    public String getJoinTable() {
	    return joinTable;
	}
}
