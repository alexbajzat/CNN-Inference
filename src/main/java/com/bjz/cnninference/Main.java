package com.bjz.cnninference;

import com.bjz.cnninference.activations.ReLUActivation;
import com.bjz.cnninference.layers.ActivationSimpleLayer;
import com.bjz.cnninference.layers.ComplexLayer;
import com.bjz.cnninference.layers.MaxPoolingComplexLayer;
import com.bjz.cnninference.layers.SimpleLayer;

/**
 * Created on 2/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        double[][] a = {{1, 1}};
        double[][] weight = {{1, 1, 1, 1}, {1, 1, 1, 1}};
        double[][] biases = {{1, 1, 1, 1}};
        double[][][] b = {{{-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}}
                , {{-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}}};  // (2,2,2)

        SimpleLayer simpleLayer = new ActivationSimpleLayer(new ReLUActivation(), weight, biases);
        double[][] result = simpleLayer.forward(a);

        ComplexLayer complexLayer = new MaxPoolingComplexLayer();
        double[][][] result2 = complexLayer.forward(b);


    }
}
