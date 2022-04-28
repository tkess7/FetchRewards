package com.example.fetchrewards.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payer {

    public Payer() {
    }

    public Payer(String payer, int points) {
        this.payer = payer;
        this.points = points;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String payer;
    private int points;

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
