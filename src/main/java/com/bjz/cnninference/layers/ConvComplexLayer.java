package com.bjz.cnninference.layers;

import com.bjz.cnninference.activations.Activation;
import com.bjz.cnninference.exceptions.InvalidConfigurationException;
import com.bjz.cnninference.layers.apis.ComplexLayer;
import com.bjz.cnninference.utils.MathUtils;
import com.bjz.cnninference.utils.ObjectUtils;

/**
 * Created on 2/9/2018.
 */
public class ConvComplexLayer implements ComplexLayer {
    private Activation activation;
    private int filterHeight;
    private int filterWidth;
    private boolean keepRatio;
    private double[][][][] filters;
    private int stride;

    /***
     *
     * @param filters       kernels for convolution
     * @param stride        convolution step
     * @param keepRatio     flag whether to pad the input or not
     * @param activation    activation object
     */
    public ConvComplexLayer(double[][][][] filters, int stride, boolean keepRatio, Activation activation) {
        this.stride = stride;
        this.filters = filters;
        this.keepRatio = keepRatio;
        this.filterHeight = filters[0][0].length;
        this.filterWidth = filters[0][0][0].length;
        this.activation = activation;
    }

    /***
     * the non-activation constructor
     * other params keep syntactic meaning
     */
    public ConvComplexLayer(double[][][][] filters, int stride, boolean keepRatio) {
        this(filters, stride, keepRatio, null);
    }

    public double[][][] forward(double[][][] x) {
        if (keepRatio) {
            if (this.stride != 1) {
                throw new InvalidConfigurationException("Cannot 0 pad if stride != 1");
            }
            x = MathUtils.zeroPad(x, (this.filterHeight - 1) / 2, (this.filterWidth - 1) / 2);
        }
        double[][][] weighted = MathUtils.convolve(x, this.filters, this.stride);
        if (!ObjectUtils.isNull(activation)) {
            return this.activation.apply(weighted);
        }
        return weighted;
    }
}
