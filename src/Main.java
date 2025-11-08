import data.Instance;
import math.ActivationFunction;
import math.LossFunction;
import network.NeuralNetwork;

import java.util.List;

public class Main {

    private static final String CSV_PATH = "./data/diabetes.csv";
    private static final int N_OUTPUTS = 1;

    public static void main(String[] args) throws Exception {
        List<Instance> instances = Instance.readFromFile(CSV_PATH, ",", N_OUTPUTS);

        int inputSize = instances.getFirst().inputs.length;
        int outputSize = instances.getFirst().outputs.length;
        NeuralNetwork nn = new NeuralNetwork(inputSize, new int[]{}, outputSize, ActivationFunction.SCHWELLENWERT, ActivationFunction.SCHWELLENWERT, LossFunction.MSE);
        nn.train(instances);

        // TODO: testing
    }

}
