package com.StudentLoginv01.StudentLoginv01.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.StudentLoginv01.StudentLoginv01.message.ResponseMessage;
import com.StudentLoginv01.StudentLoginv01.model.*;
import com.StudentLoginv01.StudentLoginv01.repository.UserRepository;
import com.StudentLoginv01.StudentLoginv01.service.FilesStorageService;
import com.StudentLoginv01.StudentLoginv01.payload.*;

//import com.StudentLoginv01.StudentLoginv01.security.jwt.*;
//import com.StudentLoginv01.StudentLoginv01.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
//  @Autowired
//  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  FilesStorageService storageService;

//  @Autowired
//  PasswordEncoder encoder;

//  @Autowired
//  JwtUtils jwtUtils;

//  @PostMapping("/login")
//  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//    Authentication authentication = authenticationManager
//        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//
//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());
//
//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//        .body(new UserInfoResponse(userDetails.getId(),
//                                   userDetails.getUsername(),
//                                   userDetails.getEmail(),
//                                   roles));
//  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Email is already in use!"));
    }

//    Resource imagefile = storageService.load(signUpRequest.getImage());
    
    String imageUrl = MvcUriComponentsBuilder
            .fromMethodName(FilesController.class, "getFile", signUpRequest.getImage()).build().toString();
    String videoUrl = MvcUriComponentsBuilder
            .fromMethodName(FilesController.class, "getFile", signUpRequest.getVideo()).build().toString();
    String pdfUrl = MvcUriComponentsBuilder
            .fromMethodName(FilesController.class, "getFile", signUpRequest.getCertification()).build().toString();
    
    
    // Create new user's account
    User user = new User(signUpRequest.getName(),
    					 signUpRequest.getSurname(),
                         signUpRequest.getEmail(),
                         signUpRequest.getPassword(),
                         signUpRequest.getCountry(),
                         signUpRequest.getLanguage(),
                         signUpRequest.getHeadline(),
                         signUpRequest.getDescription(),
                         imageUrl,
                         videoUrl,
                         pdfUrl,
                         signUpRequest.getRate(),
                         signUpRequest.getRole()
                         );

    User createdUser = userRepository.save(user);

    if (createdUser == null) {
    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Create user was failed!"));
    }

    Object response = new ResponseMessage("created successfully", createdUser);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
