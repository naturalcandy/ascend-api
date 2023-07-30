package com.ascend.ascend.service;

import java.util.List;

import com.ascend.ascend.model.User;
import com.ascend.ascend.dto.UserSignUpDto;

public interface UserService {

    public User createUser(User user);

    // public User updateUser(User user);
    public void deleteUser(Long id);

    public User getUser(Long id);

    public List<User> getAllUsers();

    public void signup(UserSignUpDto signUpDto) throws Exception;

}