package com.StudentLoginv01.StudentLoginv01.controller;

import com.StudentLoginv01.StudentLoginv01.model.Admin;
import com.StudentLoginv01.StudentLoginv01.payload.AdminDto.AdminLoginRequestDTO;
import com.StudentLoginv01.StudentLoginv01.service.AdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
@RestController
public class AdminController {

	@Autowired
    private  AdminService adminService;

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adminLogin(@RequestBody @Valid AdminLoginRequestDTO adminLoginRequestDTO, HttpServletRequest request){
        Admin admin = adminService.findAdminByEmail(adminLoginRequestDTO);
        if(admin == null){
            String message = "Invalid credentials for admin : " + adminLoginRequestDTO.getEmail();
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        HttpSession session = request.getSession();
        session.setAttribute("admin",admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


    @GetMapping("/logout")
    public ResponseEntity<String> adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
    }



}
