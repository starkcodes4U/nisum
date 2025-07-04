package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<Long, User> userMap = new HashMap<>();
    private long nextId = 1;

    public User createUser(User user) {
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        if (userMap.containsKey(id)) {
            updatedUser.setId(id);
            userMap.put(id, updatedUser);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return userMap.remove(id) != null;
    }
}
