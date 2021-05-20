import java.util.ArrayList;

public class TodoList {
    ArrayList<String> stringArrayList = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        stringArrayList.add(todo.replaceAll("ADD", "").trim());
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (index < stringArrayList.size()){
            stringArrayList.add(index, todo.replaceAll("ADD(\\s+)?\\d+", "").trim());
        } else stringArrayList.add(todo.replaceAll("ADD(\\s+)?\\d+", "").trim());
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (index < stringArrayList.size()) {
            stringArrayList.remove(index);
            stringArrayList.add(index, todo.replaceAll("EDIT(\\s+)?\\d+", "").trim());
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (index < stringArrayList.size()) {
            stringArrayList.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return stringArrayList;
    }
}