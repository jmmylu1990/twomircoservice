package com.example.income.controller;

import com.example.income.dto.IncomeDTO;
import com.example.income.entity.Income;
import com.example.income.service.IncomeService;
import com.example.income.service.UserAndIncomeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Random;

@RestController
public class IncomeController {

    @Autowired
    public IncomeService incomeService;

    @Autowired
    private UserAndIncomeService userAndIncomeService;

    @PostMapping("/saveIncome")
    public void saveIncome(@RequestBody Income income){
        incomeService.saveIncome(income);
    }

    @PostMapping("/addincome/1")
    public String addIncome1(@RequestBody IncomeDTO incomeDTO) {
        userAndIncomeService.saveUserAndIncome(incomeDTO.getUserId(),incomeDTO.getAmount(), incomeDTO.getName());

        return "success!!";
    }



}
