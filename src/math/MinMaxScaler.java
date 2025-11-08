package math;

import data.Instance;

import java.util.List;

public class MinMaxScaler {

    /**
     * Applies Min-Max Normalization to the inputs of all instances.
     * This scales each feature (column) independently to the range [0, 1].
     * Scaling Formula: X := (X - min) / (max - min)
     *
     * @param instances The instances to be scaled.
     */
    public static void scaleInputs(List<? extends Instance> instances) {
        Instance first = instances.getFirst();

        // init
        int nInputs = first.inputs.length;
        double[] minInputs = new double[nInputs];
        double[] maxInputs = new double[nInputs];
        for(int i = 0; i < nInputs; i++) {
            minInputs[i] = first.inputs[i];
            maxInputs[i] = first.inputs[i];
        }

        // find min & max values
        for(Instance instance : instances) {
            for(int i = 0; i < nInputs; i++) {
                double input = instance.inputs[i];
                if(input < minInputs[i]) minInputs[i] = input;
                if(input > maxInputs[i]) maxInputs[i] = input;
            }
        }

        // scale
        for(Instance instance : instances) {
            for(int i = 0; i < nInputs; i++) {
                double featureRange = maxInputs[i] - minInputs[i];
                if (featureRange == 0) { // avoiding division by 0
                    instance.inputs[i] = 0.0; // typically set to 0
                } else {
                    instance.inputs[i] = (instance.inputs[i] - minInputs[i]) / featureRange;
                }
            }
        }
    }

}
