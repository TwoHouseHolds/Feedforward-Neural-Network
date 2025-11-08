package network;

import math.ActivationFunction;
import math.LossFunction;

import java.util.Locale;

public class Node {

    double[] weights;
    double bias;
    ActivationFunction activationFunction;
    LossFunction lossFunction;
    int indexInLayer;

    double[] tempInputs;
    double tempNetInput;
    double tempOutput;
    double tempDelta;

    public Node(int nInputs, ActivationFunction activationFunction, LossFunction lossFunction, int indexInLayer) {
        weights = new double[nInputs];
        for (int i = 0; i < nInputs; i++) {
            weights[i] = 2 * Math.random() - 1; // [-1,1)
        }
        bias = Math.random();
        this.activationFunction = activationFunction;
        this.lossFunction = lossFunction;
        this.indexInLayer = indexInLayer;
    }

    public Node(double[] weights, double bias, ActivationFunction activationFunction) {
        this.weights = weights;
        this.bias = bias;
        this.activationFunction = activationFunction;
    }

    public double forward(double[] inputs) {
        tempInputs = inputs;
        tempNetInput = netInput(inputs);
        tempOutput = activationFunction.apply(tempNetInput);
        return tempOutput;
    }

    public void backwardOutput(int actual) {
        tempDelta = activationFunction.applyDerivative(tempNetInput) // g'(z)
                * lossFunction.applyPartialDerivativeToA(actual, tempOutput); // dE / da

    }

    public void backwardHidden(Layer layerLPlus1) {
        double sum = 0.0;
        for (Node previousNode : layerLPlus1.nodes) {
            sum += previousNode.weights[indexInLayer] // w_k,j
                    * previousNode.tempDelta;
        }
        tempDelta = activationFunction.applyDerivative(tempNetInput) // g'(z)
                * sum;
    }

    public void updateWeightsAndBias(double learningRate) {
        for(int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * tempInputs[i] * tempDelta;
        }
        bias += tempDelta;
    }

    public double netInput(double[] inputs) {
        if (inputs.length != weights.length) throw new IllegalArgumentException("Wrong input length!");
        double netInput = bias;
        for (int i = 0; i < weights.length; i++) {
            netInput += weights[i] * inputs[i];
        }
        return netInput;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Node{ w=[");
        for (int i = 0; i < weights.length; i++) {
            result.append(String.format(Locale.US, "%.2f", weights[i]));
            if (i != weights.length - 1) result.append(",");
        }
        result.append("], b=").append(String.format(Locale.US, "%.2f", bias)).append(" }");
        return result.toString();
    }
}