package com.bjz.cnninference.layers;

/**
 * Created on 2/9/2018.
 */
public class ConvLayer implements Layer {
    private boolean keepRation;
    private double[][][] filters;
    private int stride;
    private int fieldSize;

    public ConvLayer(double[][][] filters, int stride, int fieldSize, boolean keepRatio) {
        this.fieldSize = fieldSize;
        this.stride = stride;
        this.filters = filters;
        this.keepRation = keepRatio;
    }

    // oops... ?? different dimensions for output
    public double[][] forward(double[][] x) {
        return null;
    }
}
