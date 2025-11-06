package prediction;

import graph.HiddenLayer;
import graph.InputLayer;
import graph.Layer;
import graph.OutputLayer;

public class NeuralNetwork implements ClassificationSolver {

    Layer[] layers;

    public NeuralNetwork(int inputSize, int[] hiddenLayerSizes, int outputSize) {
        layers = new Layer[1 + hiddenLayerSizes.length + 1];

        // input layer
        layers[0] = new InputLayer(inputSize, inputSize);

        // hidden layers
        int previousLayerSize = inputSize;
        for(int i = 0; i < hiddenLayerSizes.length; i++) {
            layers[i + 1] = new HiddenLayer(hiddenLayerSizes[i], previousLayerSize);
            previousLayerSize = hiddenLayerSizes[i];
        }

        // output layer
        layers[layers.length -1] = new OutputLayer(outputSize, previousLayerSize);
    }

    @Override
    public int predictClass(double[] features) {
        return 0;
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
