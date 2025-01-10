package com.app.service;

import com.app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public User addUser(User user) {
        userList.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userList;
    }
}
