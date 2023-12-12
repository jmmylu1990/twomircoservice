package com.example.income.service;

import com.example.income.entity.Income;
import com.example.income.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeService {

    void saveIncome(Income income);
}
