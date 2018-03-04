package com.bjz.cnninference.model;

import com.bjz.cnninference.layers.api.ComplexLayer;
import com.bjz.cnninference.layers.api.SimpleLayer;
import com.bjz.cnninference.layers.api.TransitionLayer;
import com.bjz.cnninference.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Brought to life by bjz on 3/4/2018.
 */
public class Model {
    private final List<SimpleLayer> simpleLayers;
    private final TransitionLayer transitionLayer;
    private final List<ComplexLayer> complexLayers;

    Model(List<SimpleLayer> simpleLayers, List<ComplexLayer> complexLayers, TransitionLayer transitionLayer) {
        this.simpleLayers = simpleLayers;
        this.complexLayers = complexLayers;
        this.transitionLayer = transitionLayer;
    }

    public static ModelBuilder builder() {
        return new ModelBuilder();
    }

    /***
     *
     * @param input raw input ( CxHxW )
     * @return array of predictions among each dimension
     */
    public int[] predict(double[][][] input) {
        for (ComplexLayer layer : complexLayers) {
            input = layer.forward(input);
        }
        double[][] flatten = transitionLayer.apply(input);
        for (SimpleLayer layer : simpleLayers) {
            flatten = layer.forward(flatten);
        }
        return MathUtils.maxIndicesX(flatten);
    }
}
