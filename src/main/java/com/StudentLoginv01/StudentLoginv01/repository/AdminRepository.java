package com.StudentLoginv01.StudentLoginv01.repository;

import com.StudentLoginv01.StudentLoginv01.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);

}
