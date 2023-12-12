package com.example.income.service.impl;

import com.example.income.entity.Income;
import com.example.income.repository.IncomeRepository;
import com.example.income.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {


    @Autowired
    public IncomeRepository incomeRepository;

    @Override
    public void saveIncome(Income income) {
        incomeRepository.save(income);
    }
}
