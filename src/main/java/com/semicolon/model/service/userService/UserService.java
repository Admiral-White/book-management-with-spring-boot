package com.semicolon.model.service.userService;

import com.semicolon.model.data.model.User;
import com.semicolon.model.web.exceptions.UserDoesNotExistException;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user) throws UserDoesNotExistException;

    User findUserById(Integer id) throws UserDoesNotExistException;

    User findByUsername(String username) throws UserDoesNotExistException;

    List<User> findAllUsers();

    void deleteUserById(Integer id) throws UserDoesNotExistException;

}
