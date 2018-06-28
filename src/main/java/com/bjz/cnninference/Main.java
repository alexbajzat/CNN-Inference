package com.bjz.cnninference;

import com.bjz.cnninference.activations.ReLUActivation;
import com.bjz.cnninference.layers.ConvComplexLayer;
import com.bjz.cnninference.layers.FlatteningTransitionLayer;
import com.bjz.cnninference.layers.HiddenSimpleLayer;
import com.bjz.cnninference.model.Model;
import com.bjz.cnninference.model.ModelBuilder;
import com.bjz.cnninference.prediction.PredictionResult;
import com.bjz.cnninference.utils.MatrixUtils;

import java.util.Random;

/**
 * Created on 2/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        double[][][][] filters = MatrixUtils.generateRandom4d(1, 3, 3, 3, 2);
        double[][] weights = MatrixUtils.generateRandom2d(100, 3, 2);
        double[] biases = MatrixUtils.generateRandom1d(3, 1);
        double[][][] target = MatrixUtils.generateRandom3d(1, 10, 10, 2);

        Model model = Model.builder()
                .addComplexLayer(new ConvComplexLayer(filters, 1, true))
                .setTransitionLayer(new FlatteningTransitionLayer())
                .addSimpleLayer(new HiddenSimpleLayer(weights, biases))
                .build();
        PredictionResult predict = model.predict(target);
    }


}
