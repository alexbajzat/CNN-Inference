package com.bjz.cnninference.prediction;

/**
 * Brought to life by bjz on 3/21/2018.
 */
public class PredictionResult {
    private double[] rawResults;
    private double[] probabilites;

    public double[] getRawResults() {
        return rawResults;
    }

    public PredictionResult setRawResults(double[] rawResults) {
        this.rawResults = rawResults;
        return this;
    }

    public double[] getProbabilites() {
        return probabilites;
    }

    public PredictionResult setProbabilites(double[] probabilites) {
        this.probabilites = probabilites;
        return this;
    }
}
