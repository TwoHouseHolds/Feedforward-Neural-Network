package data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class LinearDatasetGenerator {

    public static void generateTo(String csvPath, int nDataRows) throws IOException {
        Random random = new Random();
        StringBuilder dataset = new StringBuilder();

        // Define randomized coefficients for the linear separator: A*X1 + B*X2 = C
        double a = (random.nextDouble() * 2.0) - 1.0;
        double b = (random.nextDouble() * 2.0) - 1.0;

        // Ensure at least one coefficient is substantial
        if (Math.abs(a) < 0.1 && Math.abs(b) < 0.1) {
            a = 1.0;
        }

        // Calculate the center of the predictor (50 is the average input value)
        double cCenter = 50.0 * (a + b);

        // Limit the intercept 'c' to a small deviation around the center point for balanced classes
        final double MAX_SHIFT = 20.0;
        double cMin = cCenter - MAX_SHIFT;
        double cMax = cCenter + MAX_SHIFT;

        double c = cMin + random.nextDouble() * (cMax - cMin);

        dataset.append("Input1,Input2,Output\n");

        for (int i = 0; i < nDataRows; i++) {
            // Generate inputs X1 and X2 in range [0.0, 100.0]
            float x1 = (float) (random.nextDouble() * 100.0);
            float x2 = (float) (random.nextDouble() * 100.0);

            // Calculate the predictor value
            double predictorValue = a * x1 + b * x2;
            int y;

            // Classify based on the random line
            if (predictorValue <= c) {
                y = 1;
            } else {
                y = 0;
            }

            String line = String.format(Locale.US, "%.4f,%.4f,%d", x1, x2, y);
            dataset.append(line).append("\n");
        }

        if (!dataset.isEmpty()) {
            dataset.setLength(dataset.length() - 1);
        }

        try (FileWriter writer = new FileWriter(csvPath)) {
            writer.write(dataset.toString());
        }
    }
}