package com.example.income.service.impl;

import com.example.income.dto.IncomeDTO;
import com.example.income.entity.Income;
import com.example.income.service.IncomeService;
import com.example.income.service.UserAndIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class UserAndIncomeServiceImpl implements UserAndIncomeService {

    @Autowired
    public IncomeService incomeService;

    private final String bApplicationUrl = "http://localhost:9091"; // B应用程序的URL
    private final WebClient webClient = WebClient.create(bApplicationUrl);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndIncome(Long userId,  double amount, String name){

        // 获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();

        // 创建 Timestamp 对象
        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);

        Random random = new Random();
        long randomNumber = (long) (random.nextDouble() * 90000L) + 10000L;
        Income income = new Income();
        income.setUserId(randomNumber);
        income.setAmount(amount);
        income.setOperateDate(currentTimestamp);
        incomeService.saveIncome(income);
        String apiUrl = "/saveUser2"; // 替换为B应用程序的API端点
        // 添加概率检查，以1/2的概率抛出RuntimeException

        // 创建表示你的对象的实例
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setUserId(randomNumber);
        incomeDTO.setAmount(amount);
        incomeDTO.setName(name);
        // 发送POST请求
        String responseBody = webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(incomeDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 阻塞等待响应

        // 处理响应
        System.out.println("Response from B Application: " + responseBody);

        if (shouldThrowRuntimeException()) {
            System.out.println("拋出錯誤");
            throw new RuntimeException("Randomly throwing RuntimeException!");
        }

    }

    private boolean shouldThrowRuntimeException() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
