package network;

import data.Instance;
import math.ActivationFunction;
import math.LossFunction;

import java.util.List;

public class NeuralNetwork {

    Layer[] layers; // without input layer

    public NeuralNetwork(int inputSize, int[] hiddenLayerSizes, int outputSize, ActivationFunction hiddenActivation, ActivationFunction outputActivation, LossFunction lossFunction) {
        layers = new Layer[hiddenLayerSizes.length + 1];

        // hidden layers
        int previousLayerSize = inputSize;
        for (int i = 0; i < hiddenLayerSizes.length; i++) {
            layers[i] = new Layer(hiddenLayerSizes[i], previousLayerSize, hiddenActivation, lossFunction, LayerType.HIDDEN);
            previousLayerSize = hiddenLayerSizes[i];
        }

        // output layer
        layers[layers.length - 1] = new Layer(outputSize, previousLayerSize, outputActivation, lossFunction, LayerType.OUTPUT);
    }

    public void train(List<? extends Instance> instances, int nEpochen, double learningRateStart) throws Exception {
        double learningRate = learningRateStart;
        double learningRateDelta = (learningRateStart / nEpochen);
        for (int epoche = 0; epoche < nEpochen; epoche++) {
            for (Instance instance : instances) {
                double[] inputs = instance.inputs;
                forward(inputs);
                int[] actual = instance.outputs;
                backwardAndUpdateWnB(actual, learningRate);
            }
            // learningRate -= learningRateDelta;
        }
    }

    private double[] forward(double[] inputs) {
        double[] previousOutputs = inputs;
        for (Layer layer : layers) { // iterate over layers
            Node[] nodes = layer.nodes;
            double[] currentOutputs = new double[nodes.length];
            for (int i = 0; i < nodes.length; i++) { // iterate over nodes
                currentOutputs[i] = nodes[i].forward(previousOutputs);
            }
            previousOutputs = currentOutputs;
        }
        return previousOutputs;
    }

    private void backwardAndUpdateWnB(int[] actual, double learningRate) throws Exception {
        for(int i = layers.length - 1; i >= 0; i--) { // iterate over layers
            Layer layer = layers[i];
            Node[] nodes = layer.nodes;
            for(int j = 0; j < nodes.length; j++) { // iterate over nodes
                Node node = nodes[j];
                if(layer.type == LayerType.OUTPUT) node.backwardOutput(actual[j]);
                else if (layer.type == LayerType.HIDDEN) node.backwardHidden(layers[i + 1]);
                else throw new Exception("Input Layers should not exist!");
                node.updateWeightsAndBias(learningRate);
            }
        }
    }

    public int[] predictClass(double[] inputs) {
        double[] outputs = forward(inputs);
        int[] result = new int[outputs.length];
        for (int i = 0; i < outputs.length; i++) {
            result[i] = outputs[i] >= 0.5 ? 1 : 0;
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