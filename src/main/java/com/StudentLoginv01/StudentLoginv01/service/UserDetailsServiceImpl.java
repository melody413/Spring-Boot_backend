package com.StudentLoginv01.StudentLoginv01.service;

import com.StudentLoginv01.StudentLoginv01.controller.FilesController;
import com.StudentLoginv01.StudentLoginv01.model.Role;
import com.StudentLoginv01.StudentLoginv01.model.User;
import com.StudentLoginv01.StudentLoginv01.payload.UserDto.UpdateUserDto;
import com.StudentLoginv01.StudentLoginv01.repository.UserRepository;
import com.StudentLoginv01.StudentLoginv01.specification.UserSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest request;

    private String sName;
    private String sPass;

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            return users;
        } catch (Exception e) {
            // log the error message and return an empty list
            System.out.println("Error retrieving users: " + e.getMessage());
            return null;
        }
    }


    @Override
    public Optional<User> getUserById(Long id) {

        Optional<User> existingUser1 = userRepository.findById(id);
        if (existingUser1 == null) {
            return null;
        }
        return existingUser1;
    }

    @Override
    public User getUserByEmail(String email) {
        // Optional<User> user = userRepository.findByName(name);
        // return user.isPresent() ? user.get().toString() : "User not found with name:
        // " + name;
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            return null;
        }
        return existingUser;
        // return userRepository.findByName(name);
    }

    @Override
    public User getUserByNameAndPassword(String email, String Password) {
        // Optional<User> user = userRepository.findByName(name);
        // return user.isPresent() ? user.get().toString() : "User not found with name:
        // " + name;
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            System.out.println("Welcome");
            return null;
        } else {
            sName = existingUser.getEmail();
            String sPass = existingUser.getPassword();
            System.out.println("Welcome");
            System.out.println(Password);
            System.out.println("To");
            System.out.println(sName);
            System.out.println(sPass);
            System.out.println(sPass);
            System.out.println(sPass);
            System.out.println("for");
            if (sPass.equals(Password)) {
                System.out.println("ififififififif");
                HttpSession session = request.getSession();
                session.setAttribute("user", existingUser);
                return existingUser;
            } else {
                System.out.println("elseelseelseelse");
                return null;

            }
            // return existingUser;
        }
        // return userRepository.findByName(name);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UpdateUserDto user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        if (user.getImage() != null && !user.getImage().isEmpty() && !user.getImage().equals("")) {
            System.out.println("hello");
            String imageUrl = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", user.getImage()).build().toString();
            existingUser.setImage(imageUrl);
        }
        if (user.getCertification() != null && !user.getCertification().isEmpty() && user.getCertification().equals("")) {
            String pdfUrl = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", user.getCertification()).build().toString();
            existingUser.setCertification(pdfUrl);
        }

        if (user.getName() != null && !user.getName().isEmpty() && !user.getName().equals(""))
            existingUser.setName(user.getName());
        if (user.getSurname() != null && !user.getSurname().isEmpty() && !user.getSurname().equals(""))
            existingUser.setSurname(user.getSurname());
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().equals(""))
            existingUser.setEmail(user.getEmail());
        if (user.getCountry() != null && !user.getCountry().isEmpty() && !user.getCountry().equals(""))
            existingUser.setCountry(user.getCountry());
        if (user.getLanguage() != null && !user.getLanguage().isEmpty() && !user.getLanguage().equals(""))
            existingUser.setLanguage(user.getLanguage());
        if (user.getRate() != null && !user.getRate().equals(0)) existingUser.setRate(user.getRate());
        if (user.getHeadline() != null && !user.getHeadline().isEmpty() && !user.getHeadline().equals(""))
            existingUser.setHeadline(user.getHeadline());
        if (user.getDescription() != null && !user.getDescription().isEmpty() && !user.getDescription().equals(""))
            existingUser.setDescription(user.getDescription());

//        System.out.println(existingUser);

        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserPassword(Long id, String password) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null || password.equals("") || password.isEmpty()) {
            return null;
        }

        if (!password.equals("") && !password.isEmpty()) {
            existingUser.setPassword(password);
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
