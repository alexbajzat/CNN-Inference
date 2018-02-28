package com.bjz.cnninference;

import com.bjz.cnninference.layers.FlatteningTransitionLayer;
import com.bjz.cnninference.layers.api.ComplexLayer;
import com.bjz.cnninference.layers.MaxPoolingComplexLayer;
import com.bjz.cnninference.utils.MathUtils;

/**
 * Created on 2/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        double[][] a = {{1, 1}};
        double[][] weight = {{1, 1, 1, 1}, {1, 1, 1, 1}};
        double[][] biases = {{1, 1, 1, 1}};
        double[][][] b = {{{-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}}
                , {{-1, 1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}}};  // (2,4,4)

        ComplexLayer complexLayer = new MaxPoolingComplexLayer();
        double[][][] result2 = complexLayer.forward(b);

        double[][][] filters = {{{0, 1}, {0, 1}}
                , {{0, 1}, {0, 1}}};  // (2,2,2)

        System.out.println("Conv");
        System.out.println("To conv: ");
        printMatrix(b);
        System.out.println("Kernels");
        printMatrix(filters);
        double[][][] result3 = MathUtils.convolve(b, filters, 1);
        System.out.println("Result: ");
        printMatrix(result3);
        System.out.println("Normal");
        printMatrix(b);
        System.out.println("Flatten: ");
        double[][][] yes = new double[1][][];
        yes[0] = new FlatteningTransitionLayer().apply(b);
        printMatrix(yes);

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
}
