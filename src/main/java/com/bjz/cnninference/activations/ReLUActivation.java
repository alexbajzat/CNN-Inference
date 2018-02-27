package com.bjz.cnninference.activations;

/**
 * Created on 2/7/2018.
 */
public class ReLUActivation implements Activation {

    /***
     *
     * @param x representing element to be processed
     * @return the activation done elementwise
     */
    public double[][] apply(double[][] x) {
        double[][] processed = new double[x.length][x[0].length]; // the output
        for (int i = 0; i < processed.length; i++) {
            processed[i] = apply(x[i]);
        }
        return processed;
    }

    public double[][][] apply(double[][][] x) {
        double[][][] processed = new double[x.length][x[0].length][x[0][0].length]; // the output
        for (int i = 0; i < processed.length; i++) {
            processed[i] = apply(x[i]);
        }
        return processed;
    }


    public double[] apply(double[] x) {
        double[] processed = new double[x.length]; // the output
        for (int j = 0; j < processed.length; j++) {
            processed[j] = x[j] > 0 ? x[j] : 0; // threshold at 0 (ReLU style)
        }
        return processed;
    }


}
