package visualization;

import data.Instance;

public class InstanceVisualizable extends Instance {

    public InstanceVisualizable(double input1, double input2, int output) {
        super(new double[]{input1, input2}, new int[]{output});
    }

}
