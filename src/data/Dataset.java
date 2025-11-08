package data;

import math.MinMaxScaler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dataset {

    public List<Instance> trainingInstances = new ArrayList<>();
    public List<Instance> testingInstances = new ArrayList<>();

    public Dataset(String filePath, String csvDelimiter, int nOutputs, double trainingPercentage) throws IOException {
        List<Instance> allInstances = readFromFile(filePath, csvDelimiter, nOutputs);
        Collections.shuffle(allInstances);
        int split = (int) Math.round(allInstances.size() * trainingPercentage);
        trainingInstances.addAll(allInstances.subList(0, split));
        testingInstances.addAll(allInstances.subList(split, allInstances.size()));
    }

    private static List<Instance> readFromFile(String filePath, String csvDelimiter, int nOutputs) throws IOException {
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

        MinMaxScaler.scaleInputs(instances);
        return instances;
    }

}
