import data.Dataset;
import data.Instance;
import math.ActivationFunction;
import math.LossFunction;
import network.Layer;
import network.NeuralNetwork;
import network.Node;

import java.util.List;

public class VLBsp {

    private static final int N_EPOCHEN = 1;
    private static final double LEARNING_RATE_START = 1;

    public static void main(String[] args) throws Exception {
        uebungForeward();
    }

    private static void uebungForeward() {
        Node node = new Node(new double[]{0.46, 0.44}, -0.08, ActivationFunction.SIGMOID);
        double a = node.forward(new double[]{0.57, 0.55});
        System.out.println(a);
    }

}