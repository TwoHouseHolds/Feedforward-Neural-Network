package math;

import java.util.function.BiFunction;

public enum LossFunction {

    MSE((y, a) -> 0.5 * Math.pow(y - a, 2), (y, a) -> a-y),
    BINARY_CROSS_ENTROPY((y, a) -> {
        // Clip 'a' to prevent log(0) or log(1) which are undefined
        double epsilon = 1e-15;
        double a_clipped = Math.max(epsilon, Math.min(1.0 - epsilon, a));
        return -(y * Math.log(a_clipped) + (1.0 - y) * Math.log(1.0 - a_clipped));
    }, (y, a) -> {
        // Clip 'a' to prevent division by zero
        double epsilon = 1e-15;
        double a_clipped = Math.max(epsilon, Math.min(1.0 - epsilon, a));
        // This is the derivative dE/da
        return (a_clipped - y) / (a_clipped * (1.0 - a_clipped));
    });

    private final BiFunction<Double, Double, Double> function;
    private final BiFunction<Double, Double, Double> partialDerivativeToA;

    // Constructor to initialize the fields
    LossFunction(BiFunction<Double, Double, Double> function, BiFunction<Double, Double, Double> partialDerivativeToA) {
        this.function = function;
        this.partialDerivativeToA = partialDerivativeToA;
    }

    public double apply(double y, double a) {
        return function.apply(y, a);
    }

    public double applyPartialDerivativeToA(double y, double a) {
        return partialDerivativeToA.apply(y, a);
    }
}
