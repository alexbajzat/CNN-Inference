package com.bjz.cnninference.layers;

/**
 * Created on 2/6/2018.
 */
public interface Layer {
    double[][] forward(double[][] x);
}
