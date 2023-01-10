import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
// этим методом будем сравнивать для сортировки последние элементы Нода (в последнем элементе полный путь прописан)
  @Override
  public int compare(Node o1, Node o2) {
    int index1 = o1.getStringList().size();
    int index2 = o2.getStringList().size();
    return -(o1.getStringList().get(index1 - 1).compareTo(o2.getStringList().get(index2 - 1)));
  }
}
