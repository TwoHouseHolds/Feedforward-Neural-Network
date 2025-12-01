package visualization;

import data.LinearDatasetGenerator;
import math.ActivationFunction;
import math.LossFunction;
import math.MinMaxPair;
import math.MinMaxScaler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppVisualization {

    private static final String CSV_PATH = "./csvs/clusters.csv";
    private static final boolean GENERATE = CSV_PATH == "./csvs/test1.csv";
    private static final String CSV_DELIMITER = ",";
    private static final int N_DATA_ROWS = 100;

    private static final int[] HIDDEN_LAYER_STRUCTURE = new int[]{20, 10};
    private static final int N_EPOCHEN = 5000;
    private static final double LEARNING_RATE_START = 0.1;
    private static final boolean DYNAMIC_LEARNING_RATE = true;

    public static void main(String[] args) throws Exception {
        if (GENERATE) LinearDatasetGenerator.generateTo(CSV_PATH, N_DATA_ROWS);

        List<InstanceVisualizable> vInstances = readFromFileVisualizable();
        NeuralNetworkVisualizable vnn = new NeuralNetworkVisualizable(HIDDEN_LAYER_STRUCTURE, ActivationFunction.SIGMOID, ActivationFunction.SIGMOID, LossFunction.MSE);
        vnn.train(vInstances, N_EPOCHEN, LEARNING_RATE_START, DYNAMIC_LEARNING_RATE);

        Visualisation.showGui(vnn, vInstances, 1);
    }

    private static List<InstanceVisualizable> readFromFileVisualizable() throws IOException {
        List<InstanceVisualizable> vInstances = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(AppVisualization.CSV_PATH));
        lines.removeFirst(); // column names

        for(String line : lines) {
            String[] numbers = line.split(CSV_DELIMITER);
            double input1 = Double.parseDouble(numbers[0]);
            double input2 = Double.parseDouble(numbers[1]);
            int output = Integer.parseInt(numbers[2]);
            InstanceVisualizable vInstance = new InstanceVisualizable(input1, input2, output);
            vInstances.add(vInstance);
        }

        MinMaxPair mmp = MinMaxScaler.getMinMaxInputs(vInstances);
        MinMaxScaler.scaleInputs(vInstances, mmp);
        return vInstances;
    }

}