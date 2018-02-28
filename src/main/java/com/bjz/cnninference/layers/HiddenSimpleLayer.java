package com.bjz.cnninference.layers;

import com.bjz.cnninference.activations.Activation;
import com.bjz.cnninference.layers.api.SimpleLayer;
import com.bjz.cnninference.utils.MathUtils;
import com.bjz.cnninference.utils.ObjectUtils;

/**
 * Brought to life by bjz on 2/28/2018.
 */
public class HiddenSimpleLayer implements SimpleLayer {
    private Activation activation;
    private double[][] weights;
    private double[] biases;

    /***
     *
     * @param activation    activation class to be applied
     * @param weights       set of weights to be applied to input
     * @param biases        set of bias to add upon activation
     */
    public HiddenSimpleLayer(Activation activation, double[][] weights, double[] biases) {
        this.activation = activation;
        this.weights = weights;
        this.biases = biases;
    }

    /***
     *  default null activation constructor (i.e. no activation)
     * @param weights   same
     * @param biases    same
     */
    public HiddenSimpleLayer(double[][] weights, double[] biases) {
        this(null, weights, biases);
    }

    /***
     *
     * @param x     input to be processed with weights, biases and activation
     * @return processed input
     */
    public double[][] forward(double[][] x) {
        double[][] result = MathUtils.product(x, weights);
        result = MathUtils.add(result, biases);
        if (!ObjectUtils.isNull(activation)) {
            return activation.apply(result);
        }
        return result;
    }
}
