package com.example.fetchrewards.repository;

import com.example.fetchrewards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT a FROM Transaction a ORDER BY a.timestamp ASC")
    List<Transaction> getMostRecentPoints();

    @Transactional
    @Modifying
    @Query("DELETE Transaction a WHERE a.id = :id")
    void deleteTransaction(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Transaction a SET a.points = :points WHERE a.id = :id")
    void updateTransactionPoints(@Param("points") int updatedPoints, @Param("id") Long id);

}
