package com.example.fetchrewards.controller;

import com.example.fetchrewards.model.Payer;
import com.example.fetchrewards.service.FetchRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointBalanceController {

    @Autowired
    private FetchRewardsService fetchRewardsService;

    @GetMapping(path = "/pointBalance")
    List<Payer> payerPointBalance() {
        return fetchRewardsService.retrievePoints();
    }
}