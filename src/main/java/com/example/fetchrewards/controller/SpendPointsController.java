package com.example.fetchrewards.controller;

import com.example.fetchrewards.model.Payer;
import com.example.fetchrewards.model.Points;
import com.example.fetchrewards.service.FetchRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpendPointsController {

    @Autowired
    private FetchRewardsService fetchRewardsService;

    @PostMapping(path = "/spendPoints", consumes = "application/json", produces = "application/json")
    List<Payer> spendPoints(@RequestBody Points points) {
        return fetchRewardsService.spendPoints(points);
    }

}
