package com.StudentLoginv01.StudentLoginv01.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
public class PageRequestDto {

    private Integer pageNum = 0;
    private Integer pageSize = 10;

    private String sortByColumn = "id";
    private Sort.Direction sort = Sort.Direction.ASC;
    
    public Integer getPageNum() {
	    return pageNum;
	}
    
    public void setPageNum(Integer pageNum) {
    	this.pageNum = pageNum;
    }
    
    public Integer getPageSize() {
	    return pageSize;
	}
    
    public void setPageSize(Integer pageSize) {
    	this.pageSize = pageSize;
    }
    
    public String getSortByColumn() {
	    return sortByColumn;
	}
    
    public void setortByColumn(String sortByColumn) {
    	this.sortByColumn = sortByColumn;
    }
    
    public Sort.Direction getSort() {
    	return sort;
	}
    
    public void setSort(Sort.Direction sort) {
    	this.sort = sort;
    }

    public Pageable getPageable(PageRequestDto dto){
        Integer page = Objects.nonNull(dto.getPageNum()) ? dto.getPageNum() : this.pageNum;
        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;
        Sort.Direction sort = Objects.nonNull(dto.getSort()) ? dto.getSort() : this.sort;
        String sortByColumn = Objects.nonNull(dto.getSortByColumn()) ? dto.getSortByColumn() : this.sortByColumn;

        // PageRequest request = PageRequest.of(page, size);
        PageRequest request = PageRequest.of(page, size, sort, sortByColumn);

        return request;
    }

}
