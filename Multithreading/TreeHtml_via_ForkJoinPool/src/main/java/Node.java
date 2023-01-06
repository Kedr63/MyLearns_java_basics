import java.util.List;

public class Node {
private final List<String> stringList;

  public Node(List<String> stringList) {
    this.stringList = stringList;
  }

  public List<String> getStringList() {
    return stringList;
  }
}
