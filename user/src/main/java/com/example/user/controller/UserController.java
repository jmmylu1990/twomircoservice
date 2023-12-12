package com.example.user.controller;

import com.example.user.dto.IncomeDTO;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/saveUser2")
    public void saveUser2(@RequestBody IncomeDTO incomeDTO){

        System.out.println("incomeDTO:"+incomeDTO.getName());
        User user = new User();
        user.setName(incomeDTO.getName());
        userService.saveUser(user);
    }
}
