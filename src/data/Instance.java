package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Instance {

    public double[] inputs;
    public int[] outputs;

    public Instance(double[] inputs, int[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static List<Instance> readFromFile(String filePath, String csvDelimiter, int nOutputs) throws IOException {
        List<Instance> instances = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(filePath));
        lines.removeFirst(); // column names

        for(String line : lines) {
            String[] numbers = line.split(csvDelimiter);

            // inputs
            double[] inputs = new double[numbers.length - nOutputs];
            for(int i = 0; i < inputs.length; i++) {
                inputs[i] = Double.parseDouble(numbers[i]);
            }

            // outputs
            int[] outputs = new int[nOutputs];
            for(int i = 0; i < nOutputs; i++) {
                outputs[i] = Integer.parseInt(numbers[inputs.length + i]);
            }

            Instance instance = new Instance(inputs, outputs);
            instances.add(instance);
        }

        minMaxScaleInputs(instances);
        return instances;
    }

    @Override
    public String toString() {
        String formattedInputs = Arrays.stream(inputs)
                .mapToObj(d -> String.format(Locale.US, "%.2f", d))
                .collect(Collectors.joining(","));
        return "Instance{ " +
                "inputs=[" + formattedInputs + ']' +
                ", outputs=" + Arrays.toString(outputs) +
                " }";
    }

    /**
     * Applies Min-Max Normalization to the inputs of all instances.
     * This scales each feature (column) independently to the range [0, 1].
     * Scaling Formula: X := (X - min) / (max - min)
     *
     * @param instances The instances to be scaled.
     */
    protected static void minMaxScaleInputs(List<? extends Instance> instances) {
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