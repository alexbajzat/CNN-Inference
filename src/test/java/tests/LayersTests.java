package tests;

import com.bjz.cnninference.layers.ConvComplexLayer;
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
