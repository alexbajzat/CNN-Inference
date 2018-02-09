package com.bjz.cnninference.layers;

/**
 * Created on 2/9/2018.
 */
public class ConvSimpleComplexLayer implements ComplexLayer {
    private boolean keepRation;
    private double[][][] filters;
    private int stride;
    private int fieldSize;

    public ConvSimpleComplexLayer(double[][][] filters, int stride, int fieldSize, boolean keepRatio) {
        this.fieldSize = fieldSize;
        this.stride = stride;
        this.filters = filters;
        this.keepRation = keepRatio;
    }

    public double[][][] forward(double[][][] x) {
        return new double[0][][];
    }
}
