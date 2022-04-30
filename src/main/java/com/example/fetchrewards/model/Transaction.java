package com.example.fetchrewards.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue
    private Long id;

    private String payer;
    private int points;
    private Date timestamp;

    public Transaction() {
    }

    public Transaction(String payer, int points, Date timestamp) {
        this.payer = payer;
        this.points = points;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
