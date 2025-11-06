package graph;

public abstract class Layer {

    Node[] nodes;

    public Layer(int nNodes, int nNodeInputs) {
        nodes = new Node[nNodes];
        for (int i = 0; i < nNodes; i++) {
            nodes[i] = new Node(nNodeInputs);
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
