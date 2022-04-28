package com.example.fetchrewards.service;

import java.util.HashMap;
import java.util.Map;

public class SpendPointsHelper {

    Map<String, Integer> subtractedPoints = new HashMap<>();

    public Map<String, Integer> getSubtractedPoints() {
        return subtractedPoints;
    }

    public void subtractPoints(String payer, int pointsToSubtract) {

        subtractedPoints.computeIfPresent(payer, (k, v) -> v + (-1 * pointsToSubtract));
        subtractedPoints.putIfAbsent(payer, -1 * pointsToSubtract);
    }

}
