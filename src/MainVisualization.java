import data.LinearDatasetGenerator;
import data.Visualisation;
import data.VisualizableInstance;
import math.ActivationFunction;
import math.LossFunction;
import network.VisualizableNeuralNetwork;

import java.util.List;

public class MainVisualization {

    private static final String CSV_PATH = "./data/test1.csv";
    private static final boolean GENERATE = true;
    private static final int N_DATA_ROWS = 333;

    public static void main(String[] args) throws Exception {
        if (GENERATE) LinearDatasetGenerator.generateTo(CSV_PATH, N_DATA_ROWS);
        List<VisualizableInstance> vInstances = VisualizableInstance.readFromFile(CSV_PATH, ",");

        VisualizableNeuralNetwork vnn = new VisualizableNeuralNetwork(new int[]{}, ActivationFunction.SCHWELLENWERT, ActivationFunction.SCHWELLENWERT, LossFunction.MSE);
        vnn.train(vInstances);

        Visualisation.showGui(vnn, vInstances, 1);
    }

}