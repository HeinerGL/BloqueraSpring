package com.project.bloquera.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.bloquera.models.MyUserDetails;
import com.project.bloquera.models.User;
import com.project.bloquera.repositories.UserJpaRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    public MyUserDetailsService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not found."));
        return new MyUserDetails(user);
    }
}
