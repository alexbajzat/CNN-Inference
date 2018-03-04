package com.bjz.cnninference;

import com.bjz.cnninference.activations.ReLUActivation;
import com.bjz.cnninference.layers.ConvComplexLayer;
import com.bjz.cnninference.layers.FlatteningTransitionLayer;
import com.bjz.cnninference.layers.HiddenSimpleLayer;
import com.bjz.cnninference.layers.api.ComplexLayer;
import com.bjz.cnninference.layers.MaxPoolingComplexLayer;
import com.bjz.cnninference.layers.api.SimpleLayer;
import com.bjz.cnninference.model.Model;
import com.bjz.cnninference.utils.MathUtils;
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import java.util.Random;

/**
 * Created on 2/8/2018.
 */
public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        double[][][] input = generateRandom3d(1, 10, 10);
        double[][][] kernels = generateRandom3d(3, 2, 2);
        double[][] weights = generateRandom2d(4, 2);
        double[] biases1 = generateRandom1d(2);
        double[][] weights2 = generateRandom2d(2, 5);
        double[] biases2 = generateRandom1d(5);

        Model model = Model.builder()
                .addComplexLayer(new ConvComplexLayer(kernels, 2, false))
                .addComplexLayer(new ConvComplexLayer(kernels, 2, true, new ReLUActivation()))
                .setTransitionLayer(new FlatteningTransitionLayer())
                .addSimpleLayer(new HiddenSimpleLayer(new ReLUActivation(), weights, biases1))
                .addSimpleLayer(new HiddenSimpleLayer(new ReLUActivation(), weights2, biases2))
                .build();
        model.predict(input);


    }

    public static void printMatrix(double a[][][]) {
        for (int n = 0; n < a.length; n++) {
            System.out.println(String.format("Channel %d", n));
            for (int i = 0; i < a[n].length; i++) {
                for (int j = 0; j < a[n][i].length; j++) {
                    System.out.printf(a[n][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void printMatrix(double a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.printf(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][][] generateRandom3d(int depth, int height, int width) {
        double[][][] result = new double[depth][height][width];

        for (int k = 0; k < depth; k++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result[k][i][j] = random.nextInt() % 2;
                }
            }
        }
        return result;
    }

    public static double[][] generateRandom2d(int height, int width) {
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = random.nextInt() % 2;
            }
        }

        return result;
    }

    public static double[] generateRandom1d(int width) {
        double[] result = new double[width];
        for (int j = 0; j < width; j++) {
            result[j] = random.nextInt() % 2;
        }
        return result;
    }

}
