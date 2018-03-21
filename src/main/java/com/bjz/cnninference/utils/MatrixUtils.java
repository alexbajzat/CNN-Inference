package com.bjz.cnninference.utils;

import java.util.Random;

/**
 * Brought to life by bjz on 3/21/2018.
 */
public class MatrixUtils {
    private static Random random = new Random();

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

    public static double[][][] generateRandom3d(int depth, int height, int width, int max) {
        double[][][] result = new double[depth][height][width];

        for (int k = 0; k < depth; k++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result[k][i][j] = random.nextInt() % max;
                }
            }
        }
        return result;
    }

    public static double[][] generateRandom2d(int height, int width, int max) {
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = random.nextInt() % max;
            }
        }

        return result;
    }

    public static double[] generateRandom1d(int width, int max) {
        double[] result = new double[width];
        for (int j = 0; j < width; j++) {
            result[j] = random.nextInt() % max;
        }
        return result;
    }
}
