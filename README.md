# Feedforward Neural Network

A custom implementation of a Multi-Layer Perceptron (MLP) built from scratch in pure Java, supporting backpropagation, dynamic learning rates, and decision boundary visualization.

## Project Structure

* **Core Network (`network/`)**
    * **`Node.java`**: Represents a single neuron. Handles weight initialization (Xavier), activation functions, and gradient calculation.
    * **`Layer.java`**: Groups nodes into Hidden or Output layers.
    * **`NeuralNetwork.java`**: Manages layers and executes the training loop (forward pass and backpropagation).
* **Data Handling (`data/`)**
    * **`Dataset.java`**: Handles CSV parsing, shuffling, train/test splitting, and MinMax scaling.
    * **`LinearDatasetGenerator.java`**: Generates synthetic linear data for testing.
* **Visualization (`visualization/`)**
    * **`Visualisation.java`**: A Swing-based GUI that renders data points and the network's decision boundary.

## Usage

### 1. Training FFN on Diabetes-Data
Run `App.main()` to train the network on the `diabetes.csv` dataset. Output: prints the test set accuracy to the console.

### 2. Visual Demo
Run `AppVisualization.main()` to launch the GUI for a visual representation of the FFN's predictions by rendering the decision boundary as a colored background behind the actual data points. The Input-CSV must have the format `x, y, label`.

![alt text](/z_readme%20images/image.png)

### 3. Lecture Exercises
Run `LectureExamples.main()` to execute isolated forward/backward pass exercises for debugging or educational purposes.

## License/Copyright
© 2025 Colin Traub
All Rights Reserved.