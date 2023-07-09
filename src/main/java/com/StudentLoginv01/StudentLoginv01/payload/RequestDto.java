package com.StudentLoginv01.StudentLoginv01.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
	
	private List<SearchRequestDto> searchRequestDto;

	private PageRequestDto pageDto;
	
	private GlobalOperator globalOperator;
	
	public enum GlobalOperator {
		AND, OR;
	}
	
	public GlobalOperator getGlobalOperator() {
		return globalOperator;
	}
	
	public List<SearchRequestDto> getSearchRequestDto() {
		return searchRequestDto;
	}
	
	public void setSearchRequestDto(List<SearchRequestDto> searchRequestDto) {
		this.searchRequestDto = searchRequestDto;
	}
	
	public PageRequestDto getPageDto() {
		return pageDto;
	}
}
