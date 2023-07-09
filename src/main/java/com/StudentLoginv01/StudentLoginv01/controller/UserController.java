package com.StudentLoginv01.StudentLoginv01.controller;

import java.util.List;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.StudentLoginv01.StudentLoginv01.model.Role;
import com.StudentLoginv01.StudentLoginv01.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.StudentLoginv01.StudentLoginv01.service.UserService;
import com.StudentLoginv01.StudentLoginv01.specification.FiltersSpecification;
import com.StudentLoginv01.StudentLoginv01.message.ResponseMessage;
import com.StudentLoginv01.StudentLoginv01.model.User;
import com.StudentLoginv01.StudentLoginv01.repository.UserRepository;
import com.StudentLoginv01.StudentLoginv01.payload.UserDto.UpdateUserDto;
import com.StudentLoginv01.StudentLoginv01.payload.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FiltersSpecification<User> userFiltersSpecification;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> user = userRepository.findAll();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getStudents")
    public ResponseEntity<Object> getAllStudent() {
        List<User> user = userRepository.findByRole(Role.student.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getTutors")
    public ResponseEntity<Object> getAllTutors() {
        List<User> user = userRepository.findByRole(Role.teacher.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> getUsersByFilter(@RequestBody RequestDto requestDto) {
        Specification<User> searchSpecification = userFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());

        List<User> users = userRepository.findAll(searchSpecification);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/search/pagination")
    public ResponseEntity<Object> getUsersByFilterPagination(@RequestBody RequestDto requestDto) {
        Specification<User> searchSpecification = userFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());

        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageDto());

        Page<User> users = userRepository.findAll(searchSpecification, pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            String message = "No user found with ID: " + id;
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByName(@PathVariable String email) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            String message = "No user found with email: " + email;
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<Object> getUserByNameAndPassword(@RequestParam String email, @RequestParam String Password) {
        User user = userService.getUserByNameAndPassword(email, Password);
        if (user == null) {
            String message = "Invalid credentials for user : " + email;
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (user.getRole().equals("teacher") && (user.getStatus() == null || user.getStatus().equals("") || !user.getStatus().equals("1"))) {

            String message = "Wait For Admin Approval : " + email;
            logger.info(message);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("Logout Successfull", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public String getUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("Email is already in use!"));
        }
        logger.info("===========================");
        logger.info(user.toString());
        logger.info("===========================");

        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            String message = "Data Not Entered";
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto user) {
        User updatedUser = userService.updateUser(id, user);

        if (updatedUser == null) {
            String message = "Record Not updated.";
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.NOT_FOUND);
        } else {
            Object response = new ResponseMessage("Record Updated", updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<Object> updateUserPassword(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUserPassword(id, user.getPassword());

        if (updatedUser == null) {
            String message = "Record Not updated.";
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.NOT_FOUND);
        } else {
            Object response = new ResponseMessage("Record Updated", updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            Optional<User> user=userRepository.findById(id);
            try{
                bookingRepository.deleteByTutor(user.get());
            }catch (Exception exception){
                System.out.println(exception);
            }
            userService.deleteUser(id);
            return new ResponseEntity<>("Record Deleted", HttpStatus.OK);
        } catch (Exception e) {
            // log the error message and return an empty list
            String message = "Record Not Deleted.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<Object> approvedTutorByAdmin(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) {
            String message = "No user found with ID: " + id;
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            String message = "User is approved For Login.";
            user.get().setStatus("1");
            userRepository.save(user.get());		
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @RequestMapping("/*")
    public String notFound() {
        return "404";
    }
}
