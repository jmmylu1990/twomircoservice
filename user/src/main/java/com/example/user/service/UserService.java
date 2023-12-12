package com.example.user.service;

import com.example.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    void saveUser(User user);

}
