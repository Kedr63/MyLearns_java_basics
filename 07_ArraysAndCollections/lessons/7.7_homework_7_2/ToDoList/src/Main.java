import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String todo = scanner.nextLine();
            if (todo.contains("exit")) {
                break;
            }

            if (todo.contains("ADD")) {
                if (todo.matches("ADD(\\s+)?\\d+.+")) {
                    int index = Integer.parseInt(todo.replaceAll("ADD(\\s+)?(\\d+).+", "$2"));
                    todoList.add(index, todo);
                } else todoList.add(todo);
            }

            if (todo.contains("LIST")) {
                for (int i = 0; i < todoList.getTodos().size(); i++) {
                    System.out.println(i + " - " + todoList.getTodos().get(i));
                }
            }

            if (todo.contains("EDIT")) {
                int index = Integer.parseInt(todo.replaceAll("EDIT(\\s+)?(\\d+).+", "$2"));
                todoList.edit(todo, index);
            }

            if (todo.contains("DELETE")) {
                int index = Integer.parseInt(todo.replaceAll("DELETE(\\s+)?(\\d+)", "$2"));
                todoList.delete(index);
            }
        }
    }

}
