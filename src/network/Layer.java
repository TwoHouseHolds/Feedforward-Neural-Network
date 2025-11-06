package network;

import math.ActivationFunction;

public class Layer {

    public Node[] nodes;

    public Layer(int nNodes, int nNodeInputs, ActivationFunction activationFunction) {
        nodes = new Node[nNodes];
        for (int i = 0; i < nNodes; i++) {
            nodes[i] = new Node(nNodeInputs, activationFunction);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Node n : nodes) {
            result.append(n.toString()).append("   ");
        }
        return result.toString();
    }
}
