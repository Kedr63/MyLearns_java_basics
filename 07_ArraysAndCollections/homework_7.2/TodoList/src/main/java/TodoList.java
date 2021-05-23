import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<String> stringList = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        stringList.add(todo.replaceAll("ADD", "").trim());
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (validateIndex(index)) {
            stringList.add(index, todo.replaceAll("ADD(\\s+)?\\d+", "").trim());
        } else stringList.add(todo.replaceAll("ADD(\\s+)?\\d+", "").trim());
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (validateIndex(index)) {
            stringList.remove(index);
            stringList.add(index, todo.replaceAll("EDIT(\\s+)?\\d+", "").trim());
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (validateIndex(index)) {
            stringList.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return (ArrayList<String>) stringList;
    }

    public boolean validateIndex (int index){
        return index < stringList.size();
    }
}