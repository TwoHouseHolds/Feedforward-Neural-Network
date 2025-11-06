import data.Instance;
import data.VisualisationJoerg;
import prediction.ClassificationSolver;
import prediction.NeuralNetwork;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Instance> instances = Instance.readFromFile("./data/test1.csv", ",", 1);
        ClassificationSolver cs = new NeuralNetwork(2, new int[]{}, 1);
        VisualisationJoerg.showGui(cs, instances, 2);
    }

}