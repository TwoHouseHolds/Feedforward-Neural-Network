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
    public double tempDelta;

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

    public double forward(double[] inputs) {
        tempInputs = inputs;
        tempNetInput = netInput(inputs);
        tempOutput = activationFunction.apply(tempNetInput);
        return tempOutput;
    }

    public void backwardOutput(int actual) {
        double gStrichVonZ = activationFunction.applyDerivative(tempNetInput);
        double dEDurch_da = lossFunction.applyPartialDerivativeToA(actual, tempOutput);
        tempDelta = gStrichVonZ * dEDurch_da;
    }

    public void backwardHidden(Layer layerLPlus1) {
        double sum = 0.0;
        for (Node nodeInLayerLPlus1 : layerLPlus1.nodes) {
            sum += nodeInLayerLPlus1.weights[this.indexInLayer] // w_k,j
                    * nodeInLayerLPlus1.tempDelta;
        }
        tempDelta = activationFunction.applyDerivative(tempNetInput) // g'(z)
                * sum;
    }

    public void updateWeightsAndBias(double learningRate) {
        for(int i = 0; i < weights.length; i++) {
            weights[i] -= learningRate * tempInputs[i] * tempDelta;
        }
        bias -= learningRate * tempDelta;
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

    public Node(double[] weights, double bias, ActivationFunction activationFunction) {
        this.weights = weights;
        this.bias = bias;
        this.activationFunction = activationFunction;
    }

    public Node(double tempNetInput, double tempOutput, ActivationFunction activationFunction, LossFunction lossFunction) {
        this.tempNetInput = tempNetInput;
        this.tempOutput = tempOutput;
        this.activationFunction = activationFunction;
        this.lossFunction = lossFunction;
    }

    public Node(double[] weights, double bias, double tempDelta) {
        this.weights = weights;
        this.bias = bias;
        this.tempDelta = tempDelta;
    }

    public Node(double tempNetInput, double tempOutput, ActivationFunction activationFunction) {
        this.tempNetInput = tempNetInput;
        this.tempOutput = tempOutput;
        this.activationFunction = activationFunction;
    }

}