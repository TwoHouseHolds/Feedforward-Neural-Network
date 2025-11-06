package data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class LinearDatasetGenerator {

    public static void generateTo(String csvPath, int nDataRows) throws IOException {
        Random random = new Random();
        StringBuilder dataset = new StringBuilder();

        dataset.append("Input1,Input2,Output\n");

        for (int i = 0; i < nDataRows; i++) {
            int x1 = random.nextInt(1101) + 100;
            float x2 = (random.nextInt(41) + 5) / 100.0f;

            double predictorValue = x1 + x2;
            int y;

            if (predictorValue <= 800.0) {
                y = 1;
            } else {
                y = 0;
            }

            String line = String.format(Locale.US, "%d,%.2f,%d", x1, x2, y);
            dataset.append(line).append("\n");
        }

        if (!dataset.isEmpty()) {
            dataset.setLength(dataset.length() - 1);
        }

        FileWriter writer = new FileWriter(csvPath);
        writer.write(dataset.toString());
        writer.close();
    }
}
