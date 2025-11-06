package network;

import data.Instance;
import math.ActivationFunction;
import math.LossFunction;

import java.util.List;

public class NeuralNetwork implements ClassificationSolver {

    Layer[] layers; // without input layer

    public NeuralNetwork(int inputSize, int[] hiddenLayerSizes, int outputSize, ActivationFunction hiddenActivation, ActivationFunction outputActivation, LossFunction lossFunction) {
        layers = new Layer[hiddenLayerSizes.length + 1];

        // hidden layers
        int previousLayerSize = inputSize;
        for(int i = 0; i < hiddenLayerSizes.length; i++) {
            layers[i] = new Layer(hiddenLayerSizes[i], previousLayerSize, hiddenActivation);
            previousLayerSize = hiddenLayerSizes[i];
        }

        // output layer
        layers[layers.length - 1] = new Layer(outputSize, previousLayerSize, outputActivation);
    }

    // TODO: WRONG FOR FFNs
    public void train(List<Instance> instances) {
        for(int epoche = 0; epoche < 20_000; epoche++) {
            for(Instance instance : instances) {
                double[] inputs = instance.inputs;
                int prediction = predictClass(inputs);
                int actual = instance.outputs[0];
                int delta = actual - prediction;

                // update weights
                for(Layer layer : layers) {
                    for(Node node : layer.nodes) {
                        double[] weights = node.weights;
                        for(int i = 0; i < weights.length; i++) {
                            weights[i] = weights[i] + 0.01 * inputs[i] * delta;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int predictClass(double[] inputs) {
        double[] previousInputs = inputs;
        for (Layer layer : layers) {
            double[] currentOutputs = new double[layer.nodes.length];
            for (int i = 0; i < layer.nodes.length; i++) {
                currentOutputs[i] = layer.nodes[i].inputsToOutput(previousInputs);
            }
            previousInputs = currentOutputs;
        }
        return previousInputs[0] >= 0.5 ? 1 : 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Layer l : layers) {
            result.append(l.toString()).append("\n");
        }
        return result.toString();
    }
}
