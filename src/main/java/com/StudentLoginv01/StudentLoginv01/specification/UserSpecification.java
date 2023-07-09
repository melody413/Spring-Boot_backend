package com.StudentLoginv01.StudentLoginv01.specification;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.StudentLoginv01.StudentLoginv01.model.User;

public class UserSpecification {
	public static Specification<User> getSpecs(
			String msn, 
			String gtin, 
			String identifierExists, 
			String customLabel2,
			String customLabel3, 
			String customLabel4, 
			String promotionId, 
			String isGoogleActive,
			String isFacebookActive, 
			String isCriteoActive, 
			String availability) 
	{
		
		Specification<User> spec=null;
		Specification<User> temp=null;
		
		if(msn!=null) {
			temp=getPlafeedByCloumn("msn",msn);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(gtin!=null) {
			temp=getPlaFeedByGtin(gtin);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(identifierExists!=null) {
			spec=getPlaFeedByIdentifierExists(identifierExists);
			temp=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if( customLabel2!=null) {
			temp=getPlaFeedByCustomLabel2(customLabel2);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(customLabel3!=null) {
			temp=getPlaFeedByCustomLabel3(customLabel3);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(customLabel4!=null) {
			temp=getPlaFeedByCustomLabel4(customLabel4);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if( promotionId!=null) {
			temp=getPlaFeedByPromotionId(promotionId);
			temp=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(isGoogleActive!=null) {
			temp=getPlaFeedByIsGoogleActive(isGoogleActive);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if( isFacebookActive!=null) {
			temp=getPlaFeedByIsFacebookActive(isFacebookActive);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(isCriteoActive!=null) {
			temp=getPlaFeedByIsCriteoActive(isCriteoActive);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		if(availability!=null) {
			temp=getPlaFeedByAvailability(availability);
			spec=spec!=null?Specification.where(spec).and(temp):temp;
		}
		
		
		return spec;
	}
	
	private static<T> Specification<T> getPlafeedByCloumn(String columnName, Object value ){
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get(columnName)), "%" +value.toString().toLowerCase() +"%");
		};
	}
	
	private static Specification<User> getPlaFeedByAvailability(String availability) {
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get("availability")), "%"+availability.toLowerCase()+"%");
		};
	}

	private static Specification<User> getPlaFeedByIsCriteoActive(String isCriteoActive) {
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get("isCriteoActive")), "%"+isCriteoActive.toLowerCase()+"%");
		};
	}

	private static Specification<User> getPlaFeedByIsFacebookActive(String isFacebookActive) {
		return (rt,qry,cb)->{
			return cb.like(cb.lower(rt.get("isFacebookActive")),"%"+isFacebookActive+"%");
		};
	}

	private static Specification<User> getPlaFeedByIsGoogleActive(String isGoogleActive) {
		return (root,qry,cb)->{
			return cb.like(cb.lower(root.get("isGoogleActive")), "%"+isGoogleActive.toLowerCase()+"%");
		};
	}

	private static Specification<User> getPlaFeedByPromotionId(String promotionId) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("promotionId")), "%"+promotionId.toLowerCase()+"%");
		};
	}

	private static Specification<User> getPlaFeedByCustomLabel4(String customLabel4) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("customLabel4")),"%"+customLabel4.toLowerCase()+"%");
		};
	}


	private static Specification<User> getPlaFeedByCustomLabel3(String customLabel3) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("customLabel3")),"%" +customLabel3.toLowerCase() + "%");
		};
	}

	private static Specification<User> getPlaFeedByCustomLabel2(String customLabel2) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("customLabel2")), "%" +customLabel2.toLowerCase() +"%");
		};
	}

	private static Specification<User> getPlaFeedByIdentifierExists(String identifierExists) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("identifierExists")), "%"+identifierExists.toLowerCase()+ "%");
		};
	}

	private static Specification<User> getPlaFeedByGtin(String gtin) {
		return (root,query,cb)->{
			return cb.like(cb.lower(root.get("gtin")), "%"+gtin.toLowerCase() +"%");
		};
	}

	private static Specification<User> getPlaFeedByMsn(String msn) {
		return (root,query,criteriaBuilder)->{
			return criteriaBuilder.like(criteriaBuilder.lower(root.get("msn")),"%"+msn.toLowerCase()+"%");
		};
	}
	
	public static Specification<User> getSpecsByMap(Map<String, Object> map) {
		Specification<User> spec=null;
				Specification<User> tempSpec=null;
		for (Map.Entry<String, Object>entry : map.entrySet()) {
			System.out.println(entry.getKey() + " "+entry.getValue());
			tempSpec=getPlafeedByCloumn(entry.getKey(),entry.getValue());
			spec=spec!=null?Specification.where(spec).and(tempSpec):tempSpec;
		}
		return spec;
	}
}
