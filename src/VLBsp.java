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
        uebungBackwardHidden();
    }

    private static void uebungForeward() {
        Node node = new Node(new double[]{0.46, 0.44}, -0.08, ActivationFunction.SIGMOID);
        double a = node.forward(new double[]{0.57, 0.55});
        System.out.println(a);
    }

    private static void uebungBackwardOutput() {
        Node node = new Node(-0.847297860387, 0.3, ActivationFunction.SIGMOID, LossFunction.MSE);
        node.backwardOutput(1);
        System.out.println(node.tempDelta);
    }

    private static void uebungBackwardHidden() {
        Node node00 = new Node(0.1, 0.5, ActivationFunction.SIGMOID);
        Node node10 = new Node(new double[]{0.6, 0.4}, -0.1, 0.5);
        Node node11 = new Node(new double[]{0.1, -0.3}, 0.2, 0.7);
        Layer layer1 = new Layer(new Node[]{node10, node11});
        node00.backwardHidden(layer1);
        System.out.println(node00.tempDelta);
    }

}