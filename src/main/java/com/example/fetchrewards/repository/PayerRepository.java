package com.example.fetchrewards.repository;

import com.example.fetchrewards.model.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PayerRepository extends JpaRepository<Payer, Long> {

    @Query("SELECT a FROM Payer a WHERE a.payer = :payer")
    Payer retrievePayer(@Param("payer") String payer);

    @Transactional
    @Modifying
    @Query("UPDATE Payer a SET a.points = a.points + :points WHERE a.payer = :payer")
    void updatePayerBalance(@Param("payer") String payer, @Param("points") int points);
}
