package com.bjz.cnninference;

import com.bjz.cnninference.utils.MathUtils;

/**
 * Created on 2/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        double[][] a = {{1, 1, 1}, {1, 1, 1}};
        double[][] b = {{1, 1}, {1, 1}, {1, 1}};
        double[][] c = {{1, 1}};

        double[][] result = MathUtils.product(a, b);
        double[][] result2 = MathUtils.product(c, a);
    }
}
