package com.example.login.service;

import com.example.login.model.Role;
import com.example.login.model.User;
import com.example.login.repositories.RoleRepository;
import com.example.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userResUserRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userResUserRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user){
        Role userRole = roleRepository.findByRole("ADMIN");
        user.builder()
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .active(1)
                .roles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

        return user;
    }
}
