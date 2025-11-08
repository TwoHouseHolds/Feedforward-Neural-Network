package math;

import java.util.function.DoubleFunction;

public enum ActivationFunction {

    SIGMOID(x -> 1.0 / (1.0 + Math.exp(-x)), x -> {
        double sigmoid = 1.0 / (1.0 + Math.exp(-x));
        return sigmoid * (1 - sigmoid);
    }),
    SCHWELLENWERT(x -> (x >= 0 ? 1.0 : 0.0), null);

    final DoubleFunction<Double> function;
    final DoubleFunction<Double> derivative;

    // Constructor to initialize the fields
    ActivationFunction(DoubleFunction<Double> function, DoubleFunction<Double> derivative) {
        this.function = function;
        this.derivative = derivative;
    }

    public double apply(double d) {
        return function.apply(d);
    }

    public double applyDerivative(double d) {
        return derivative.apply(d);
    }

}
