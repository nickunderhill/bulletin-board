package com.underhill.nick.bulletinboard.service.impl;

import com.underhill.nick.bulletinboard.model.User;
import com.underhill.nick.bulletinboard.repository.RoleRepository;
import com.underhill.nick.bulletinboard.repository.UserRepository;
import com.underhill.nick.bulletinboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
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

    @Override
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

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    /**
     * Automatically authenticates user in the application.
     * @param user - User object which will be authenticated
     */
    @Override
    public void authWithoutPassword(User user){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null,
                userDetailsServiceImpl.loadUserByUsername(user.getEmail()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Updates given user's Security context.
     * This method should be used to immediately apply changes in the User object email or roles.
     * @param user - to update User
     */
    @Override
    public void updatePrincipal(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> updatedAuthorities = userDetailsServiceImpl.loadUserByUsername(user.getEmail()).getAuthorities();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmail(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
