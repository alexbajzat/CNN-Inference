package com.bjz.cnninference.layers;

import com.bjz.cnninference.exceptions.InvalidArgumentException;
import com.bjz.cnninference.layers.apis.ComplexLayer;

/**
 * Created on 2/8/2018.
 */
public class MaxPoolingComplexLayer implements ComplexLayer {
    private final int fieldSize = 2;
    private final int stride = 2;

    /***
     * default value constructor
     * reduces data to half it`s size
     * works with even shaped matrices
     */
    public MaxPoolingComplexLayer() {
    }

    public double[][][] forward(double[][][] x) {
        // assumming fields don`t overlap
        if (x[0].length % fieldSize != 0 || x[0][0].length % fieldSize != 0) {
            throw new InvalidArgumentException(String.format("Cannot perform pooling with field size: %d and stride: %d on shape(%d, %d, %d)"
                    , fieldSize, stride, x.length, x[0].length, x[0][0].length));
        }
        double[][][] result = new double[x.length][x[0].length / fieldSize][x[0][0].length / fieldSize];

        // compute maxpooling on each feature
        for (int feature = 0; feature < x.length; feature++) {
            for (int i = 0; i < x[feature].length / fieldSize; i++) {
                for (int j = 0; j < x[feature][i].length / fieldSize; j++) {
                    double max = Double.NEGATIVE_INFINITY;

                    for (int k = i * stride; k < i * stride + fieldSize; k++) {
                        for (int l = j * stride; l < j * stride + fieldSize; l++) {
                            if (x[feature][k][l] > max) {
                                max = x[feature][k][l];
                            }
                        }
                    }
                    result[feature][i][j] = max;
                }
            }
        }
        return result;
    }

}
