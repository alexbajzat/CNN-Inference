package com.bjz.cnninference.layers;

import com.bjz.cnninference.activations.Activation;

/**
 * Created on 2/9/2018.
 */
public class ConvComplexLayer implements ComplexLayer {
    private Activation activation;
    private int filterHeight;
    private int filterWidth;
    private boolean keepRation;
    private double[][][] filters;
    private int stride;

    public ConvComplexLayer(double[][][] filters, int stride, boolean keepRatio, Activation activation) {
        this.stride = stride;
        this.filters = filters;
        this.keepRation = keepRatio;
        this.filterHeight = filters[0].length;
        this.filterWidth = filters[0][0].length;
        this.activation = activation;
    }

    public double[][][] forward(double[][][] x) {
        return new double[0][][];
    }
}
