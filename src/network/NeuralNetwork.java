package network;

import data.Instance;
import math.ActivationFunction;
import math.LossFunction;

import java.util.List;

public class NeuralNetwork {

    Layer[] layers; // without input layer
    private static final int N_EPOCHEN = 500;
    private static final double LEARNING_RATE = 0.09;

    public NeuralNetwork(int inputSize, int[] hiddenLayerSizes, int outputSize, ActivationFunction hiddenActivation, ActivationFunction outputActivation, LossFunction lossFunction) {
        layers = new Layer[hiddenLayerSizes.length + 1];

        // hidden layers
        int previousLayerSize = inputSize;
        for (int i = 0; i < hiddenLayerSizes.length; i++) {
            layers[i] = new Layer(hiddenLayerSizes[i], previousLayerSize, hiddenActivation);
            previousLayerSize = hiddenLayerSizes[i];
        }

        // output layer
        layers[layers.length - 1] = new Layer(outputSize, previousLayerSize, outputActivation);
    }

    // TODO: WRONG FOR FFNs
    public void train(List<? extends Instance> instances) {
        double dynamicLearningRate = LEARNING_RATE;
        double learningRateDelta = (LEARNING_RATE / N_EPOCHEN) * 0.9999999999; // so learning rate stays positive
        for (int epoche = 0; epoche < N_EPOCHEN; epoche++) {
            for (Instance instance : instances) {
                double[] inputs = instance.inputs;
                int prediction = predictClass(inputs)[0]; // TODO
                int actual = instance.outputs[0];
                int delta = actual - prediction;

                // update weights AND BIAS
                for (Layer layer : layers) {
                    for (Node node : layer.nodes) {
                        double[] weights = node.weights;
                        for (int i = 0; i < weights.length; i++) {
                            weights[i] = weights[i] + (dynamicLearningRate * inputs[i] * delta);
                        }
                        node.bias = node.bias + (dynamicLearningRate * delta);
                    }
                }
            }
            // dynamicLearningRate -= learningRateDelta;
        }
    }

    public int[] predictClass(double[] inputs) {
        double[] previousInputs = inputs;
        for (Layer layer : layers) {
            double[] currentOutputs = new double[layer.nodes.length];
            for (int i = 0; i < layer.nodes.length; i++) {
                currentOutputs[i] = layer.nodes[i].inputsToOutput(previousInputs);
            }
            previousInputs = currentOutputs;
        }
        int[] result = new int[previousInputs.length];
        for (int i = 0; i < previousInputs.length; i++) {
            result[i] = previousInputs[i] >= 0.5 ? 1 : 0;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Layer l : layers) {
            result.append(l.toString()).append("\n");
        }
        return result.toString();
    }
}