package network;

import math.ActivationFunction;
import math.LossFunction;

public class VisualizableNeuralNetwork extends NeuralNetwork implements VisualizableClassificationSolver{

    public VisualizableNeuralNetwork(int[] hiddenLayerSizes, ActivationFunction hiddenActivation, ActivationFunction outputActivation, LossFunction lossFunction) {
        super(2, hiddenLayerSizes, 1, hiddenActivation, outputActivation, lossFunction);
    }

    @Override
    public int predictClassVisualizable(double input1, double input2) {
        return predictClass(new double[]{input1, input2})[0];
    }

}
