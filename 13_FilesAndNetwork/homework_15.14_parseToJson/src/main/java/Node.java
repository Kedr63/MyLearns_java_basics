import java.util.Set;

public class Node implements Comparable<Node> {
    private Set<Station> nodeConnections;

    public Node(Set<Station> nodeConnections) {
        this.nodeConnections = nodeConnections;
    }

    public Set<Station> getNodeConnections() {
        return nodeConnections;
    }

    public void setNodeConnections(Set<Station> nodeConnections) {
        this.nodeConnections = nodeConnections;
    }

    @Override
    public int compareTo(Node anotherNode) {
        int res = 0;
        for (Station station : this.nodeConnections) {
            for (Station anotherStation : anotherNode.getNodeConnections()) {
                if (station == null || anotherStation == null) {
                    return -1;
                }
                int tmp = station.compareTo(anotherStation);
                if (tmp == 0) {
                    res++;
                }
            }
        }
        if (res == nodeConnections.size()) {
            return 0;
        } else return -1;
    }
}
