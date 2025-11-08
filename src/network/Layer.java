package network;

import math.ActivationFunction;
import math.LossFunction;

public class Layer {

    public Node[] nodes;
    LayerType type;

    public Layer(int nNodes, int nNodeInputs, ActivationFunction activationFunction, LossFunction lossFunction, LayerType type) {
        nodes = new Node[nNodes];
        for (int i = 0; i < nNodes; i++) {
            nodes[i] = new Node(nNodeInputs, activationFunction, lossFunction, i);
        }
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Node n : nodes) {
            result.append(n.toString()).append("   ");
        }
        return result.toString();
    }

    public Layer(Node[] nodes) {
        this.nodes = nodes;
    }

}
