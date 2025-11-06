import data.Instance;
import data.LinearDatasetGenerator;
import data.VisualisationJoerg;
import math.ActivationFunction;
import math.LossFunction;
import network.NeuralNetwork;

import java.util.List;

public class Main {

    private static final String CSV_PATH = "./data/test1.csv";
    private static final int N_DATA_ROWS = 100;
    private static final int N_OUTPUTS = 1;

    private static final double LEARNING_RATE = 0.01;

    public static void main(String[] args) throws Exception {
        LinearDatasetGenerator.generateTo(CSV_PATH, N_DATA_ROWS);
        List<Instance> instances = Instance.readFromFile(CSV_PATH, ",", N_OUTPUTS);

        int inputSize = instances.getFirst().inputs.length;
        int outputSize = instances.getFirst().outputs.length;
        NeuralNetwork nn = new NeuralNetwork(inputSize, new int[]{}, outputSize, ActivationFunction.SIGMOID, ActivationFunction.SIGMOID, LossFunction.MSE);
        nn.train(instances, LEARNING_RATE);

        VisualisationJoerg.showGui(nn, instances, 2);
    }

}