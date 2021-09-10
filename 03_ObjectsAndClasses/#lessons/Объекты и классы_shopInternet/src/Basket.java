public class Basket { // Корзина

    private String items = "";         // 2.
    private int totalPrice = 0;        // 5.
    private int limit;                 // 6.1

    public Basket() {
        items = "Список товаров: ";
        limit = 100000;
    }

    public Basket(int totalPriceLimit) {
        this();       // здесь вызывается конструктор без параметров (чтоб не повторять код), и поэтому ~ items
        limit = totalPriceLimit;   // инизиализируем только один раз в конструкторе без параметров
    }

    // здесь сначала сработает конст-р без параметров и потом к /this.items/ добавится наш список
    // товаров /items/
    public Basket(String items, int totalPrice) {   // 7.
        this();
        this.items = this.items + items;  //this обозначает что эта переменная ЭТОГО класса
        this.totalPrice = totalPrice;
    }

    public void add(String name, int price) {     // 1.  и чтоб этот метод продолжал работать -->
        add(name, price, 1);  // добавим сюда метод и количество -1шт
    }

    // здесь перегрузка метода add() или - overload
    public void add(String name, int price, int count) {    //1.
        if (contains(name)) { // если корзина содержит этот товар  --> то
            return;   // на этой строке выполнение этого метода завершится
        }
        if (totalPrice + count * price >= limit) {     // 6.2
            return;
        }
        items = items + "\n" + name + " - " + count + "шт. " + "\t" + price + " руб.";
        totalPrice = totalPrice + count * price;
    }

    public void clear() {  // 3.
        items = "";        // создадим пустую строку, т.о. очищая корзину
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {   // 4.
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }

}
