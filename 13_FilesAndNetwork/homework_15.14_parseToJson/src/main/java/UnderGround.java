import java.util.ArrayList;
import java.util.List;

public class UnderGround {
    private List<Line> lineList;
    private List<Node> nodeList;

    public UnderGround() {
        this.lineList = new ArrayList<>();
        this.nodeList = new ArrayList<>();
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        return "UnderGround{" + "lineList=" + lineList + ", nodeList=" + nodeList + '}';
    }
}
