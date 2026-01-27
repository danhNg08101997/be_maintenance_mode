package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users u = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole())
                .build();
    }
}
