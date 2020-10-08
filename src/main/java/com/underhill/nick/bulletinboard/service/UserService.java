package com.underhill.nick.bulletinboard.service;

import com.underhill.nick.bulletinboard.model.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email);

    boolean createUser(User newUser);

    boolean update(User user);

    List<User> getAllUsers();

    boolean isAuthenticated();

    void authWithoutPassword(User user);

    public void updatePrincipal(User user);

}
