package com.bjz.cnninference.model;

import com.bjz.cnninference.layers.apis.ComplexLayer;
import com.bjz.cnninference.layers.apis.SimpleLayer;
import com.bjz.cnninference.layers.apis.TransitionLayer;
import com.bjz.cnninference.prediction.PredictionResult;
import com.bjz.cnninference.utils.MathUtils;

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
     * @return {@link PredictionResult} containing probabilites and raw results for each index
     */
    public PredictionResult predict(double[][][] input) {
        for (int i = 0; i < complexLayers.size(); i++) {
            input = complexLayers.get(i).forward(input);
        }
        double[][] flatten = transitionLayer.apply(input);
        for (int i = 0; i < simpleLayers.size(); i++) {
            flatten = simpleLayers.get(i).forward(flatten);
        }

        double[] rawResult = flatten[0];
        double[] probabilites = MathUtils.probabilityVector(rawResult);

        return new PredictionResult()
                .setProbabilites(probabilites)
                .setRawResults(rawResult);
    }
}
