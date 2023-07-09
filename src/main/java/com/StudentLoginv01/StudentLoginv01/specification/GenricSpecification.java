package com.StudentLoginv01.StudentLoginv01.specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

public class GenricSpecification {

	public static <T> Specification<T> getSpecification(Map<String,Object> map){
		int count=0;
		Specification<T> spec=null;
		Specification<T> tempSpec=null;
		for(Map.Entry<String,Object> entry:map.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("pageNumber")|| entry.getKey().equalsIgnoreCase("pageSize"))
			 continue;
			
			tempSpec=findByColumn(entry.getKey(),entry.getValue());
			spec=spec!=null?Specification.where(spec).and(tempSpec):tempSpec;
			System.out.println(spec.toString() +" ***********"+  ++count);
		}
		return spec;
	}

	private static<T> Specification<T> findByColumn(String columnName, Object value) {
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get(columnName)), "%"+value.toString().toLowerCase()+"%");
		};
	}
	
	private static <T>Specification<T> findAllByColumnName(String columnName, Object value){
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get(columnName)),"%"+value+"%")						;
		};
		
	}
	
}
