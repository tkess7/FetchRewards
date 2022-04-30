package com.example.fetchrewards.service;

import com.example.fetchrewards.model.Payer;
import com.example.fetchrewards.model.Points;
import com.example.fetchrewards.model.Transaction;
import com.example.fetchrewards.repository.PayerRepository;
import com.example.fetchrewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FetchRewardsServiceImplTest {

    @Autowired
    private FetchRewardsServiceImpl fetchRewardsService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PayerRepository payerRepository;

    @Test
    public void createTransaction() {
        Transaction input = new Transaction("DANNON", 1000, new Date());
        Transaction output = fetchRewardsService.createTransaction(input);

        assertEquals(input, output);
    }

    @Test
    public void spendPoints() {
        Transaction input = new Transaction("DANNON", 1000, new Date());
        fetchRewardsService.createTransaction(input);
        List<Payer> payer = fetchRewardsService.spendPoints(new Points(123));
        assertEquals(1, payer.size());
        assertEquals("DANNON", payer.get(0).getPayer());
        assertEquals(-123, payer.get(0).getPoints());
    }

    @Test
    public void retrievePoints() {
        Transaction input = new Transaction("DANNON", 1000, new Date());
        fetchRewardsService.createTransaction(input);
        List<Payer> payer = fetchRewardsService.retrievePoints();
        assertEquals(1000, payer.get(0).getPoints());
        assertEquals("DANNON", payer.get(0).getPayer());
    }

}
