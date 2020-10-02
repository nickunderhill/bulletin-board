package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.RoleRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Transactional
    public boolean createUser(User newUser) {
        if (userRepository.getUserByEmail(newUser.getEmail()) != null) {
            return false;
        }
        newUser.setRoles(roleRepository.findAllByName("USER"));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    @Transactional
    public boolean update(User user) {
        if (user.getId() != null) {
            User updateUser = userRepository.getOne(user.getId());
            if(!updateUser.getEmail().equals(user.getEmail())) {
                updateUser.setEmail(user.getEmail());
                updatePrincipal(updateUser);
                System.out.println("Updated principle");
            }
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            if (user.getPassword().length() > 0) {
                updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(updateUser);
            return true;
        }
        return false;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public void updatePrincipal(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> updatedAuthorities = customUserDetailsService.loadUserByUsername(user.getEmail()).getAuthorities();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmail(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
