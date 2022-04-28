package com.example.fetchrewards.service;

import com.example.fetchrewards.model.Payer;
import com.example.fetchrewards.model.Points;
import com.example.fetchrewards.model.Transaction;
import com.example.fetchrewards.repository.PayerRepository;
import com.example.fetchrewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FetchRewardsServiceImpl implements FetchRewardsService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayerRepository payerRepository;

    /**
     * Takes input related to a transaction and determines if the payer is new or existing. For new payers, a payer record will be created
     * and for existing, points will be updated appropriately.
     *
     * @param transaction
     * @return
     */
    @Override
    public Transaction createTransaction(Transaction transaction) {
        if(payerRepository.retrievePayer(transaction.getPayer()) == null) {
            payerRepository.save(new Payer(transaction.getPayer(), transaction.getPoints()));
        } else {
            payerRepository.updatePayerBalance(transaction.getPayer(), transaction.getPoints());
        }
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves all transactions in order to remove the payers points based on transaction timestamp. Also, no payer points can go negative.
     * @param points input Points object that is the number of points being spent
     * @return List of Payer with the amount of points spent
     */
    @Override
    public List<Payer> spendPoints(Points points) {

        SpendPointsHelper spentPointsHelper = new SpendPointsHelper();

        //The order of these transactions is extremely important
        //The transaction with the oldest timestamp will be the first element, and with the most recent timestamp, the last element.
        List<Transaction> sortedTransactions = transactionRepository.getMostRecentPoints();

        int pointsToRemove = points.getPoints();

        for(Transaction transaction : sortedTransactions) {
            if(pointsToRemove == 0) {
                break;
            } else if (transaction.getPoints() < pointsToRemove) {
                pointsToRemove -= transaction.getPoints();
                spentPointsHelper.subtractPoints(transaction.getPayer(), transaction.getPoints());

                //Delete transaction since points are gone for that transaction
                transactionRepository.deleteTransaction(transaction.getId());
            } else {
                int updatePoints = transaction.getPoints() - pointsToRemove;
                spentPointsHelper.subtractPoints(transaction.getPayer(), pointsToRemove);
                transactionRepository.updateTransactionPoints(updatePoints, transaction.getId());

                pointsToRemove = 0;
            }
        }

        for(Map.Entry<String, Integer> entry : spentPointsHelper.getSubtractedPoints().entrySet()) {
            payerRepository.updatePayerBalance(entry.getKey(), entry.getValue());
        }

        List<Payer> payerList = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : spentPointsHelper.getSubtractedPoints().entrySet()) {
            payerList.add(new Payer(entry.getKey(), entry.getValue()));
        }

        return payerList;
    }

    @Override
    public List<Payer> retrievePoints() {
       return payerRepository.findAll();
    }
}
