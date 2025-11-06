package graph;

import java.util.Arrays;
import java.util.Locale;

public class Node {

    double[] weights;
    double bias;

    public Node(int nInputs) {
        weights = new double[nInputs];
        for(int i = 0; i < nInputs; i++) {
            weights[i] = Math.random();
        }
        bias = Math.random();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Node{ w=[");
        for(int i = 0; i < weights.length; i++) {
            result.append(String.format(Locale.US, "%.2f", weights[i]));
            if(i != weights.length - 1) result.append(",");
        }
        result.append("], b=").append(String.format(Locale.US, "%.2f", bias)).append(" }");
        return result.toString();
    }
}
