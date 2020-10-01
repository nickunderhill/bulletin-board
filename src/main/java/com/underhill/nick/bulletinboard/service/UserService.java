package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.RoleRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
