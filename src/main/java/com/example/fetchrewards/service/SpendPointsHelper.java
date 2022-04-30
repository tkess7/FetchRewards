package com.example.fetchrewards.service;

import java.util.HashMap;
import java.util.Map;

public class SpendPointsHelper {

    /**
     * pointsToRemoveForPayer is a Map collection that contains a String of the payer name as the key, and an integer of the points to remove
     * as the value.
     *
     * For example, the map could look like this:
     * "UNILEVER": -200
     * "MILLER COORS": -4700
     * "DANNON": -100
     */
    Map<String, Integer> pointsToRemoveForPayer = new HashMap<>();

    public Map<String, Integer> getPointsToRemoveForPayer() {
        return pointsToRemoveForPayer;
    }

    public void subtractPoints(String payer, int pointsToRemove) {

        //If the payer is present, add to the total of points to remove
        pointsToRemoveForPayer.computeIfPresent(payer, (k, v) -> v + (-1 * pointsToRemove));

        //If the payer is not present, add them to the map with the points to remove
        pointsToRemoveForPayer.putIfAbsent(payer, -1 * pointsToRemove);
    }

}
