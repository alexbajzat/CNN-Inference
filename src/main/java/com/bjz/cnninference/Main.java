package com.bjz.cnninference;

import com.bjz.cnninference.activations.ReLUActivation;
import com.bjz.cnninference.layers.ActivationLayer;
import com.bjz.cnninference.layers.Layer;
import com.bjz.cnninference.layers.MaxPoolingLayer;
import com.bjz.cnninference.utils.MathUtils;

/**
 * Created on 2/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        double[][] a = new double[100][100];


        Layer layer = new MaxPoolingLayer();
        long start = System.currentTimeMillis();
        double[][] result = layer.forward(a);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Finished in %d ms", end - start));

    }
}
