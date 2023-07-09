package com.StudentLoginv01.StudentLoginv01.service;

import com.StudentLoginv01.StudentLoginv01.model.Admin;
import com.StudentLoginv01.StudentLoginv01.payload.AdminDto.AdminLoginRequestDTO;

public interface AdminService {

    Admin findAdminByEmail(AdminLoginRequestDTO adminLoginRequestDTO);

}
