package com.bjz.cnninference.layers;

import com.bjz.cnninference.layers.api.TransitionLayer;

import static java.lang.System.arraycopy;

/**
 * Brought to life by bjz on 2/28/2018.
 */
public class FlatteningTransitionLayer implements TransitionLayer {
    /***
     * transpose the input from DxHxW to 1xD*H*W
     * @param target    input to be flattened
     * @return transposed target
     */
    public double[][] apply(double[][][] target) {
        double[][] flatten = new double[1][target.length * target[0].length * target[0][0].length];
        int startIndex = 0;
        for (double[][] slice : target) {
            for (double[] col : slice) {
                arraycopy(col, 0, flatten[0], col.length * startIndex++, col.length);
            }
        }
        return flatten;
    }
}
