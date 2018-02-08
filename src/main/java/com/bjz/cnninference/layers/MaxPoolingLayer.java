package com.bjz.cnninference.layers;

import com.bjz.cnninference.exceptions.InvalidArgumentException;

/**
 * Created on 2/8/2018.
 */
public class MaxPoolingLayer implements Layer {
    private int fieldSize = 2;
    private int stride = 2;

    //todo add custom params feature for future release
//    public MaxPoolingLayer(int stride, int fieldSize) {
//        this.stride = stride;
//        this.fieldSize = fieldSize;
//    }

    /***
     * default value constructor
     * reduces data to half it`s size
     * works with even shaped matrices
     */
    public MaxPoolingLayer() {
    }

    public double[][] forward(double[][] x) {
        // assumming fields don`t overlap
        if (x.length % fieldSize != 0) {
            throw new InvalidArgumentException(String.format("Cannot perform pooling with field size: %d and stride: %d on shape(%d, %d)"
                    , fieldSize, stride, x.length, x[0].length));
        }
        double[][] result = new double[x.length / fieldSize][x[0].length / fieldSize];
        for (int i = 0; i < x.length / fieldSize; i++) {
            for (int j = 0; j < x[i].length / fieldSize; j++) {
                double max = Double.MIN_VALUE;

                for (int k = i * stride; k < i * stride + fieldSize; k++) {
                    for (int l = j * stride; l < j * stride + fieldSize; l++) {
                        if (x[k][l] > max) {
                            max = x[k][l];
                        }
                    }
                }
                result[i][j] = max;
            }
        }
        return result;
    }

}
