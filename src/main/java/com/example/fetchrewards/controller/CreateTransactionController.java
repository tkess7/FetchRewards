package com.example.fetchrewards.controller;

import com.example.fetchrewards.model.Transaction;
import com.example.fetchrewards.service.FetchRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTransactionController {

    @Autowired
    private FetchRewardsService fetchRewardsService;

    @PostMapping(path = "/transaction", consumes = "application/json", produces = "application/json")
    Transaction addTransaction(@RequestBody Transaction transaction) {
        return fetchRewardsService.createTransaction(transaction);
    }
}
