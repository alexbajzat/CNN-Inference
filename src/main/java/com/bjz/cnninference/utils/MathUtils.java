package com.bjz.cnninference.utils;

import com.bjz.cnninference.exceptions.InvalidArgumentException;

/**
 * Created on 2/7/2018.
 */
public class MathUtils {

    public static double[][] product(double[][] a, double[][] b) {
        checkProductCompatibility(a, b);

        double[][] result = new double[a.length][b[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] product(double[] a, double[][] b) {
        checkProductCompatibility(a, b);

        double[][] result = new double[a.length][b[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result.length; k++) {
                    result[i][j] += a[k] *
                            b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] add(double[][] a, double[][] b) {
        checkAddCompatibility(a, b);
        double[][] result = new double[a.length][a[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private static void checkProductCompatibility(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new InvalidArgumentException(String.format("Incompatible shapes %d and %d", a.length, b.length));
        }
    }

    private static void checkProductCompatibility(double[] a, double[][] b) {
        if (a.length != b[0].length) {
            throw new InvalidArgumentException(String.format("Incompatible shapes %d and %d", a.length, b.length));
        }
    }

    private static void checkAddCompatibility(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new InvalidArgumentException(String.format("Cannot add shapes (%d, %d) and (%d, %d)", a.length, a[0].length, b.length, b[0].length));
        }
    }
}
