import data.Instance;
import data.LinearDatasetGenerator;
import data.VisualisationJoerg;
import prediction.ClassificationSolver;
import prediction.NeuralNetwork;

import java.util.List;

public class Main {

    private static final String CSV_PATH = "./data/test1.csv";

    public static void main(String[] args) throws Exception {
        LinearDatasetGenerator.generateTo(CSV_PATH, 100);
        List<Instance> instances = Instance.readFromFile(CSV_PATH, ",", 1);
        ClassificationSolver cs = new NeuralNetwork(2, new int[]{}, 1);
        VisualisationJoerg.showGui(cs, instances, 2);
    }

}