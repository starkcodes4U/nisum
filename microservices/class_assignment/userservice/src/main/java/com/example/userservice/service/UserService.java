package com.example.userservice.service;

import com.example.userservice.model.User;
import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}
