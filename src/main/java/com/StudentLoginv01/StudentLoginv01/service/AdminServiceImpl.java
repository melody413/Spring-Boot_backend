package com.StudentLoginv01.StudentLoginv01.service;

import com.StudentLoginv01.StudentLoginv01.model.Admin;

import com.StudentLoginv01.StudentLoginv01.payload.AdminDto.AdminLoginRequestDTO;
import com.StudentLoginv01.StudentLoginv01.repository.AdminRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminByEmail(AdminLoginRequestDTO adminLoginRequestDTO) {
        Optional<Admin> admin = adminRepository.findByEmail(adminLoginRequestDTO.getEmail());
        if(admin.isEmpty()){
            return null;
        }
        Admin adminFromDb = admin.get();
        if(adminFromDb.getPassword().equals(adminLoginRequestDTO.getPassword())){
            return adminFromDb;
        }
        return null;
    }
}
