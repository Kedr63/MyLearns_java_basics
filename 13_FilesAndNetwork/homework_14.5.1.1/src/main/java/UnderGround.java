import java.util.ArrayList;
import java.util.List;

public class UnderGround {
    private List<Line> lineList;
    private List<Connection> connectionList;

    public UnderGround(List<Line> lineList) {
        this.lineList = lineList;
        connectionList = new ArrayList<>();

    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    @Override
    public String toString() {
        return "UnderGround{" + "lineList=" + lineList + '}';
    }
}
