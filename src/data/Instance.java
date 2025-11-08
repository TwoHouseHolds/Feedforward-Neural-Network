package data;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class Instance {

    public double[] inputs;
    public int[] outputs;

    public Instance(double[] inputs, int[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
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
}