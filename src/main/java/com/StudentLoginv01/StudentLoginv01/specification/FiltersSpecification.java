package com.StudentLoginv01.StudentLoginv01.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.StudentLoginv01.StudentLoginv01.payload.RequestDto;
import com.StudentLoginv01.StudentLoginv01.payload.SearchRequestDto;

@Service
public class FiltersSpecification<T> {

	public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {
		
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
			}
		};
	}
	
	public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, RequestDto.GlobalOperator globalOperator) {
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			for(SearchRequestDto requestDto: searchRequestDtos) {
				
				switch (requestDto.getOperation()) {
				
					case EQUAL:
						Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
						predicates.add(equal);
						break;
						
					case LIKE:
						Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()), "%"+requestDto.getValue()+"%");
						predicates.add(like);
						break;
				
					case IN:
						String[] split = requestDto.getValue().split(",");
						Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
						predicates.add(in);
						break;
						
					case GREATER_THAN:
						Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
						predicates.add(greaterThan);
						break;
						
					case LESS_THAN:
						Predicate lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
						predicates.add(lessThan);
						break;
						
					case BETWEEN:
						// parameter: "10,20"
						String[] split1 = requestDto.getValue().split(",");
						Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), Long.parseLong(split1[0]), Long.parseLong(split1[1]));
						predicates.add(between);
						break;
					
					case JOIN:
						// example join function
						// address: join table name
						// city: join column
						Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
						predicates.add(join);
						break;
						
					default:
						throw new IllegalStateException("Unexpected value: " + "");
				}
			}
			
			if (globalOperator.equals(RequestDto.GlobalOperator.AND)) {
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			} else {
				return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
