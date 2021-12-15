import java.io.File;
import java.util.ArrayList;

// Создадим дерево в котором мы будим хранить папки и их размеры, чтоб потом распечатать это дерево
public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

    public Node(File folder, long limit) {
        this(folder);
        this.limit = limit;
    }

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimit(){
        return limit;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    // Чтобы распечатывалась вся нода правильно и все ее потомки - переопределим toString
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children) {
            if (child.getSize() < limit) {
                continue;
            }
            builder.append("   ".repeat(level) + child.toString());
        }
        return builder.toString();
    }

    private void setLevel(int level) {
        this.level = level;
    }
}
