package data;

import network.VisualizableClassificationSolver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Visualisation {

    private static VisualizerPanel panel;

    public static void showGui(VisualizableClassificationSolver solver, List<VisualizableInstance> instances, int step) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new VisualizerPanel(instances, step, solver, false);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            panel.repaint();
        });
    }

    private static class VisualizerPanel extends JPanel {
        private final List<VisualizableInstance> instances;
        private final int STEP;
        private VisualizableClassificationSolver solver;

        private final Color[] defaultBgColors = {
                new Color(255,200,200),
                new Color(200,200,255),
                new Color(200,255,200),
                new Color(255,255,200),
                new Color(255,200,255),
                new Color(200,255,255)
        };

        private final Color[] defaultPtColors = {
                Color.RED, Color.BLUE, Color.GREEN.darker(), Color.ORANGE,
                Color.MAGENTA, Color.CYAN
        };

        private final int SIZE = 500;

        public VisualizerPanel(List<VisualizableInstance> instances, int step, VisualizableClassificationSolver solver, boolean isRefresh) {
            this.instances = instances;
            this.STEP = step;
            this.solver = solver;
            setPreferredSize(new Dimension(SIZE, SIZE));
        }

        public void setSolver(VisualizableClassificationSolver solver) {
            this.solver = solver;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (solver == null) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Kein Solver verf�gbar...", 20, 20);
                return;
            }

            int MARGIN = 20; // Abstand vom Rand
            int drawableSize = SIZE - 2 * MARGIN;
            int points = drawableSize / STEP;
            int cellSize = drawableSize / points;

            // 1) Hintergrund: Entscheidungskarte
            for (int x = 0; x < points; x++) {
                for (int y = 0; y < points; y++) {
                    double fx = (double)x * STEP / drawableSize;
                    double fy = (double)y * STEP / drawableSize;

                    int clazz = solver.predictClassVisualizable(fx, fy);
                    g.setColor(defaultBgColors[clazz % defaultBgColors.length]);

                    int px = MARGIN + x * cellSize;
                    int py = SIZE - MARGIN - (y + 1) * cellSize; // obere linke Ecke
                    g.fillRect(px, py, cellSize, cellSize);
                }
            }

            // 2) Trainingspunkte
            int r = Math.max(4, cellSize / 2);
            for (Instance instance : instances) {
                int clazz = 0;
                if (instance.outputs.length == 1) { // Binary
                    clazz = (int) instance.outputs[0];
                } else { // Multi-Class
                    for (int c = 0; c < instance.outputs.length; c++) {
                        if (instance.outputs[c] == 1.0) { clazz = c; break; }
                    }
                }

                int px = MARGIN + (int)(instance.inputs[0] * drawableSize);
                int py = SIZE - MARGIN - (int)(instance.inputs[1] * drawableSize);

                g.setColor(defaultPtColors[clazz % defaultPtColors.length]);
                g.fillOval(px - r/2, py - r/2, r, r);
                g.setColor(Color.BLACK);
                g.drawOval(px - r/2, py - r/2, r, r);
            }
        }
    }
}
