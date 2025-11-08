package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VisualizableInstance extends Instance {

    public VisualizableInstance(double input1, double input2, int output) {
        super(new double[]{input1, input2}, new int[]{output});
    }

    public static List<VisualizableInstance> readFromFile(String filePath, String csvDelimiter) throws IOException {
        List<VisualizableInstance> vInstances = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(filePath));
        lines.removeFirst(); // column names

        for(String line : lines) {
            String[] numbers = line.split(csvDelimiter);
            double input1 = Double.parseDouble(numbers[0]);
            double input2 = Double.parseDouble(numbers[1]);
            int output = Integer.parseInt(numbers[2]);
            VisualizableInstance vInstance = new VisualizableInstance(input1, input2, output);
            vInstances.add(vInstance);
        }

        minMaxScaleInputs(vInstances);
        return vInstances;
    }

}
