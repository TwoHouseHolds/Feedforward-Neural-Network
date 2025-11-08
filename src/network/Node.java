package network;

import math.ActivationFunction;

import java.util.Locale;

public class Node {

    double[] weights;
    double bias;
    ActivationFunction activationFunction;

    public Node(int nInputs, ActivationFunction activationFunction) {
        weights = new double[nInputs];
        for (int i = 0; i < nInputs; i++) {
            weights[i] = 2 * Math.random() - 1; // [-1,1)
        }
        bias = Math.random();
        this.activationFunction = activationFunction;
    }

    public double inputsToOutput(double[] inputs) {
        double netInput = calculateNetInput(inputs);
        return activationFunction.apply(netInput);
    }

    public double calculateNetInput(double[] inputs) {
        if(inputs.length != weights.length) throw new IllegalArgumentException("Wrong input length!");
        double netInput = bias;
        for(int i = 0; i < weights.length; i++) {
            netInput += weights[i] * inputs[i];
        }
        return netInput;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Node{ w=[");
        for(int i = 0; i < weights.length; i++) {
            result.append(String.format(Locale.US, "%.2f", weights[i]));
            if(i != weights.length - 1) result.append(",");
        }
        result.append("], b=").append(String.format(Locale.US, "%.2f", bias)).append(" }");
        return result.toString();
    }
}