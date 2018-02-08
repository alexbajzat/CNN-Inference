package com.bjz.cnninference.layers;

/**
 * Created on 2/6/2018.
 */
interface Layer {
    double[][] forward(double[][] x);
}
