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

    public static double[][] add(double[][] a, double[] b) {
        checkAddCompatibility(a, b);
        double[][] result = new double[a.length][a[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = a[i][j] + b[j];
            }
        }
        return result;
    }

    /***
     *
     * @param a         the target on wich the convolution will be made
     * @param kernel    the filters wich will be applied among all channels
     * @param rowStart  the upper left corner index, from where to begin
     * @param colStart  the lower right corner index where it ends
     * @return the value after the convolution operation
     */
    public static double productWithKernel(double[][][] a, double[][] kernel, int rowStart, int colStart) {
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;
        int verticalBound = rowStart + kernelHeight;
        int horizontalBound = colStart + kernelWidth;

        if (a[0] == null || verticalBound > a[0].length || horizontalBound > a[0][0].length) {
            throw new InvalidArgumentException(String.format("Invalid indices for product: (%d, %d)", rowStart, colStart));
        }

        double result = 0;
        for (int depthSlice = 0; depthSlice < a.length; depthSlice++) {
            for (int i = 0; i < kernelHeight; i++) {
                for (int j = 0; j < kernelWidth; j++) {
                    result += a[depthSlice][rowStart + i][colStart + j] * kernel[i][j];
                }
            }
        }
        return result;
    }


    /***
     *
     * @param target    3 dimensional matrix which will be convolved
     * @param filters   3 dimensional matrix representing the kernels
     * @param stride    the step size of the moving kernels
     * @return 3 dimensional convolved matrix, the matrix may have shrunked size if not padded
     */
    public static double[][][] convolve(double[][][] target, double[][][] filters, int stride) {
        int filterHeight = filters[0].length;
        int filterWidth = filters[0][0].length;
        int targetHeight = target[0].length;
        int targetWidth = target[0][0].length;

        // initialize result with it`s shrunk size
        int outputDepth = target.length * filters.length;
        int outputHeight = (targetHeight - filterHeight) / stride + 1;
        int outputWidth = (targetWidth - filterWidth) / stride + 1;
        double[][][] result = new double[outputDepth][outputHeight][outputWidth];

        // convolve the target by the given stride and calculate the result for each position
        for (int k = 0; k < outputDepth; k += filters.length) {
            for (int i = 0; i < outputHeight; i += stride) {
                for (int j = 0; j < outputWidth; j += stride) {
                    // apply every filter to every channel of image
                    for (int filterN = 0; filterN < filters.length; filterN++) {
                        // calculate the convolution on each channel for position i and j
                        result[k + filterN][i][j] = productWithKernel(target, filters[filterN], i, j);
                    }
                }
            }
        }
        return result;
    }


    /**
     * @param a input to calculate max along x axis
     * @return array containing indexes of max values along each row
     */
    public static int[] maxIndicesX(double[][] a) {
        int[] indexes = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            double max = Double.NEGATIVE_INFINITY;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                    indexes[i] = j;
                }
            }
        }
        return indexes;
    }

    /***
     *
     * @param a     first operand
     * @param b     second operand
     * @exception InvalidArgumentException if the two values are product incompatible
     */
    private static void checkProductCompatibility(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new InvalidArgumentException(String.format("Incompatible shapes %d and %d", a.length, b.length));
        }
    }


    /***
     *
     * @param a     first operand
     * @param b     second operand
     * @exception InvalidArgumentException if the two values are add incompatible
     */
    private static void checkAddCompatibility(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new InvalidArgumentException(String.format("Cannot add shapes (%d, %d) and (%d, %d)", a.length, a[0].length, b.length, b[0].length));
        }
    }

    /***
     *
     * @param a     first operand
     * @param b     second operand
     * @exception InvalidArgumentException if the two values are add incompatible
     */
    private static void checkAddCompatibility(double[][] a, double[] b) {
        if (a[0].length != b.length) {
            throw new InvalidArgumentException(String.format("Cannot add shapes (%d, %d) and (%d)", a.length, a[0].length, b.length));
        }
    }
}
