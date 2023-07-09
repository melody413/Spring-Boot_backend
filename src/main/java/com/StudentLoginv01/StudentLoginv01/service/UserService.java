package com.StudentLoginv01.StudentLoginv01.service;


import com.StudentLoginv01.StudentLoginv01.model.User;
import com.StudentLoginv01.StudentLoginv01.payload.UserDto.UpdateUserDto;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByNameAndPassword(String email, String Password);


    User createUser(User user);

    User updateUser(Long id, UpdateUserDto user);

    User updateUserPassword(Long id, String password);

    void deleteUser(Long id);
}
