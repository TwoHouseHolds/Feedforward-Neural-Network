package visualization;

import math.ActivationFunction;
import math.LossFunction;
import network.NeuralNetwork;

public class NeuralNetworkVisualizable extends NeuralNetwork implements ClassificationSolverVisualizable {

    public NeuralNetworkVisualizable(int[] hiddenLayerSizes, ActivationFunction hiddenActivation, ActivationFunction outputActivation, LossFunction lossFunction) {
        super(2, hiddenLayerSizes, 1, hiddenActivation, outputActivation, lossFunction);
    }

    @Override
    public int predictClassVisualizable(double input1, double input2) {
        return predictClass(new double[]{input1, input2})[0];
    }

}
