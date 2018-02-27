package com.bjz.cnninference.activations;

/**
 * Created on 2/6/2018.
 */
public interface Activation {
    double[][][] apply(double[][][] x);

    double[][] apply(double[][] x);

    double[] apply(double[] x);
}
