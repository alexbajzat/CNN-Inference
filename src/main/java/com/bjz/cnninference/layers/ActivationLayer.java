package com.bjz.cnninference.layers;

import com.bjz.cnninference.activations.Activation;
import com.bjz.cnninference.utils.MathUtils;

/**
 * Created on 2/7/2018.
 */
public class ActivationLayer implements Layer {
    private Activation activation;
    private double[][] weights;
    private double[][] biases;

    public ActivationLayer(Activation activation, double[][] weights, double[][] biases) {
        this.activation = activation;
        this.weights = weights;
        this.biases = biases;
    }


    /***
     *
     * @param x element to be processed
     * @return activation applied elementwise
     */
    public double[][] forward(double[][] x) {
        double[][] weighted = MathUtils.product(x, weights);
        weighted = MathUtils.add(weighted, biases);
        return activation.apply(weighted);
    }

}
