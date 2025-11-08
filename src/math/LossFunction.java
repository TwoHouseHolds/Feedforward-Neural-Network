package math;

import java.util.function.BiFunction;

public enum LossFunction {

    MSE((y, a) -> 0.5 * Math.pow(y - a, 2), (y, a) -> a-y);

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
