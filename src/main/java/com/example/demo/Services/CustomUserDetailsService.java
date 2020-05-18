package com.example.demo.Services;

import java.util.Optional;

import com.example.demo.Dtos.CustomUserDetails;
import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.repositories.userRepos.AdminRepo;
import com.example.demo.repositories.userRepos.EntrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EntrantRepository userRepository;
    private final AdminRepo adminRepository;

    @Autowired
    public CustomUserDetailsService(EntrantRepository userRepository, AdminRepo adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Entrant> entrantMaybe = Optional.ofNullable(userRepository.findByEmail(username));

        if (entrantMaybe.isPresent()){
            return entrantMaybe
                    .map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));
        }else {
            Optional<Admin> adminMaybe = Optional.ofNullable(adminRepository.findByEmail(username));

            return adminMaybe
                    .map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));
        }
    }
}