package com.bjz.cnninference.activations;

/**
 * Created on 2/7/2018.
 */
public class NonActivation implements Activation {

    /***
     *
     * @param x element to be processed
     * @return the same element, as the non activation maintains the initial value
     */
    public double[][] apply(double[][] x) {
        return x;
    }
}
