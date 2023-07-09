package com.StudentLoginv01.StudentLoginv01.payload;

public enum QueryOperator {
	CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL, BEGINS_WITH, 
    DOES_NOT_BEGIN_WITH, ENDS_WITH, DOES_NOT_END_WITH, 
    NUL, NOT_NULL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN,
    LESS_THAN_EQUAL, ANY, ALL;

    public static QueryOperator getDataOption(final String dataOption){
         switch(dataOption){
            case "all": return ALL;
            case "any": return ANY;
            default: return null;
        }    
    }
}
