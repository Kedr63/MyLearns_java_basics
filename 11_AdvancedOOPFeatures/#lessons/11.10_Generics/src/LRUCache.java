import java.util.ArrayList;
import java.util.List;

public class LRUCache<T> { // зададим тип нашего элемента - Т

    ArrayList<T> elements;
    int size;

    public LRUCache(int size) {
        this.size = size;
        elements = new ArrayList<>();
    }

    public void addElement(T element)  // теперь Т можем использовать
    {
        int currentSize = elements.size(); // например размер кеша 5 элементов
        if (currentSize >= size) {         // если вдруг стало 6
            for (int i = 0; i < currentSize - size + 1; i++) { //
                elements.remove(0);   // удаляем от индекса /0/, здесь /0/ и /1/ удаляем
            }
        }
        elements.add(element);  // и добавляем в конец (кэш снова будет равен 5)

    }

    public T getElement(int i) {
        if (i >= elements.size()) {
            return null;
        }
        return elements.get(i);
    }

    public List<T> getAllElements() {
        return elements;
    }
}   // вот такой кеш мы создали, при добавлении он позволяет добавить только сколько, сколько здесь
    // указано в /size/-е
    // поэкпериментируем в методе main
