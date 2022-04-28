package com.example.fetchrewards.service;

import com.example.fetchrewards.model.Payer;
import com.example.fetchrewards.model.Points;
import com.example.fetchrewards.model.Transaction;

import java.util.List;

public interface FetchRewardsService {

    Transaction createTransaction(Transaction transaction);

    List<Payer> spendPoints(Points points);

    List<Payer> retrievePoints();
}
