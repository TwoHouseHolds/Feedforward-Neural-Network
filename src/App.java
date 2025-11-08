import data.Dataset;
import data.Instance;
import math.ActivationFunction;
import math.LossFunction;
import network.NeuralNetwork;

import java.util.Arrays;
import java.util.List;

public class App {

    private static final String CSV_PATH = "./csvs/diabetes.csv";
    private static final String CSV_DELIMITER = ",";
    private static final int N_OUTPUTS = 1;
    private static final double TRAINING_PERCENTAGE = 0.8;

    public static void main(String[] args) throws Exception {
        Dataset ds = new Dataset(CSV_PATH, CSV_DELIMITER, N_OUTPUTS, TRAINING_PERCENTAGE);

        int inputSize = ds.trainingInstances.getFirst().inputs.length;
        int outputSize = ds.trainingInstances.getFirst().outputs.length;
        NeuralNetwork nn = new NeuralNetwork(inputSize, new int[]{}, outputSize, ActivationFunction.SCHWELLENWERT, ActivationFunction.SCHWELLENWERT, LossFunction.MSE);
        nn.train(ds.trainingInstances);

        System.out.println(testAccuracy(nn, ds.testingInstances));
    }

    private static double testAccuracy(NeuralNetwork nn, List<Instance> testingInstances) {
        int correctPredictions = 0;
        for(Instance testInstance : testingInstances) {
            double[] input = testInstance.inputs;
            int[] output = nn.predictClass(input);
            int[] actual = testInstance.outputs;

            boolean correctlyPredicted = true;
            for(int i = 0; i < output.length; i++) {
                if(output[i] != actual[i]) {
                    correctlyPredicted = false;
                    break;
                }
            }
            if(correctlyPredicted) {
                correctPredictions++;
                System.out.println(Arrays.toString(output) + " " + Arrays.toString(actual) + " " + correctPredictions);
            }
        }
        return ((double) correctPredictions) / testingInstances.size();
    }

}
