package tests;

import com.bjz.cnninference.activations.ReLUActivation;
import com.bjz.cnninference.layers.ConvComplexLayer;
import com.bjz.cnninference.layers.HiddenSimpleLayer;
import com.bjz.cnninference.layers.MaxPoolingComplexLayer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Brought to life by bjz on 6/21/2018.
 */
public class LayersTests {

    @Test
    public void testConvLayer() {
        double[][][] filters = {
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}},
        };

        double[][][] target = {{
                {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
                {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}
        };

        double[][][] expected = {
                {{4, 6, 6, 4}, {6, 9, 9, 6}, {4, 6, 6, 4}},
                {{4, 6, 6, 4}, {6, 9, 9, 6}, {4, 6, 6, 4}},
                {{8, 12, 12, 8}, {12, 18, 18, 12}, {8, 12, 12, 8}},
                {{8, 12, 12, 8}, {12, 18, 18, 12}, {8, 12, 12, 8}}
        };

        ConvComplexLayer convComplexLayer = new ConvComplexLayer(filters, 1, true);
        double[][][] forward = convComplexLayer.forward(target);

        checkResultAndExpectations(expected, forward);
    }


    @Test
    public void testConvLayerWithActivation() {
        double[][][] filters = {
                {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}
        };

        double[][][] target = {{
                {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
                {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}}
        };

        double[][][] expected = {
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{4, 6, 6, 4}, {6, 9, 9, 6}, {4, 6, 6, 4}},
        };

        ConvComplexLayer convComplexLayer = new ConvComplexLayer(filters, 1, true, new ReLUActivation());
        double[][][] forward = convComplexLayer.forward(target);

        checkResultAndExpectations(expected, forward);
    }

    @Test
    public void testHiddenLayer() {
        double[][] target = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
        double[][] weights = {{1, 1, 1, 1}, {1, 1, 1, 1}};
        double[] biases = {1, 1, 1, 1};

        double[][] expected = {{3, 3, 3, 3}, {3, 3, 3, 3}, {3, 3, 3, 3}, {3, 3, 3, 3}};
        HiddenSimpleLayer layer = new HiddenSimpleLayer(weights, biases);

        double[][] forward = layer.forward(target);

        checkResultAndExpectations(expected, forward);
    }

    @Test
    public void testHiddenLayerWithActivation() {
        double[][] target = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
        double[][] weights = {{-1, 1, 1, 1}, {-1, 1, 1, 1}};
        double[] biases = {0, 0, 0, 0};

        double[][] expected = {{0, 2, 2, 2}, {0, 2, 2, 2}, {0, 2, 2, 2}, {0, 2, 2, 2}};
        HiddenSimpleLayer layer = new HiddenSimpleLayer(new ReLUActivation(), weights, biases);

        double[][] forward = layer.forward(target);

        checkResultAndExpectations(expected, forward);
    }

    @Test
    public void testMaxPoolingLayer() {
        double[][][] target = {{
                {1, -1, 0, 2},
                {0, 2, 1, 1},
                {1, 1, 1, 2},
                {1, 1, 1, 2}}
                , {{2, -1, 0, 1}
                , {-1, 1, 0, 0}
                , {1, 2, 0, 2}
                , {1, 2, 0, 2}}
        };

        double[][][] expected = {{
                {2, 2},
                {1, 2}
        }, {{2, 1}
                , {2, 2}}
        };

        MaxPoolingComplexLayer layer = new MaxPoolingComplexLayer();

        double[][][] forward = layer.forward(target);

        checkResultAndExpectations(expected, forward);

    }

    private void checkResultAndExpectations(double[][][] expected, double[][][] result) {
        for (int k = 0; k < expected.length; k++) {
            for (int i = 0; i < expected[k].length; i++) {
                for (int j = 0; j < expected[k][i].length; j++) {
                    Assert.assertTrue(expected[k][i][j] == result[k][i][j]);
                }
            }
        }
    }

    private void checkResultAndExpectations(double[][] expected, double[][] result) {
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                Assert.assertTrue(expected[i][j] == result[i][j]);
            }
        }
    }
}
