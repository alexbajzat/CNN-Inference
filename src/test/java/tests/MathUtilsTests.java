package tests;

import com.bjz.cnninference.utils.MathUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Brought to life by bjz on 6/21/2018.
 */
public class MathUtilsTests {

    @Test
    public void testProductWithKernel() {
        double[][][] target =
                {{{-1, 1, 0}, {-1, 1, 0}, {-1, 1, 0}}, //first matrix
                        {{1, -1, 0}, {1, -1, 0}, {1, -1, 0}}, //second
                        {{1, -1, 0}, {1, -1, 0}, {1, -1, 0}}}; //third

        double[][][] kernel = {{
                {2, 2, 2},
                {2, 2, 2},
                {2, 2, 2}},
                {
                        {2, 2, 2},
                        {2, 2, 2},
                        {2, 2, 2}},
                {
                        {2, 2, 2},
                        {2, 2, 2},
                        {2, 2, 2}}};

        Double v = MathUtils.productWithKernel(target, kernel, 0, 0);
        Double expectedValue = 0d;
        Assert.assertTrue(v.equals(expectedValue));
    }


    @Test
    public void testDotProduct() {
        double[][] target = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] expected = {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}};
        double[][] result = MathUtils.product(target, target);
        checkResultAndExpectations(expected, result);
    }

    @Test
    public void testAdd() {
        double[][] target = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[] bias = {1, 1, 1};
        double[][] expected = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        double[][] result = MathUtils.add(target, bias);
        checkResultAndExpectations(expected, result);
    }

    @Test
    public void testConvolution() {
        double[][][][] filters = {{
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}},
        }, {
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}},
        }};

        double[][][] target = {{
                {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
                {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}
        };

        double[][][] expected = {
                {{27, 27}, {27, 27}},
                {{27, 27}, {27, 27}}
        };

        double[][][] convolve = MathUtils.convolve(target, filters, 1);

        Assert.assertTrue(convolve.length == filters.length);

        checkResultAndExpectations(expected, convolve);

    }

    private void checkResultAndExpectations(double[][] expected, double[][] result) {
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                Assert.assertTrue(expected[i][j] == result[i][j]);
            }
        }
    }

    private void checkResultAndExpectations(double[][][] expected, double[][][] result) {
        for (int k = 0; k < expected.length; k++) {
            for (int i = 0; i < expected[k].length; i++) {
                for (int j = 0; j < expected[i].length; j++) {
                    Assert.assertTrue(expected[k][i][j] == result[k][i][j]);
                }
            }
        }
    }

}
