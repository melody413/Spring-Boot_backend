package com.StudentLoginv01.StudentLoginv01.repository;

import com.StudentLoginv01.StudentLoginv01.model.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findByEmail(String email);
	
	User findByUsername(String username);

	Boolean existsByEmail(String email);
	
	List<User> findAll(Specification<User> spec);
	
	Page<User> findAll(Specification<User> spec, Pageable pageable);

	List<User> findByRole(String role);

}
