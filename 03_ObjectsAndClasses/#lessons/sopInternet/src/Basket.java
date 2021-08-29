public class Basket {
    private String items = "";
    private int totalPrice = 0;
    private int limit;

    public Basket() {
        items = "Список товаров: ";
        limit = 100000;
    }

    public Basket(int totalPriceLimit) {
        this();
        limit = totalPriceLimit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        if (contains(name)) {
            return;
        }
        if (totalPrice + count * price >= limit) {
            return;
        }
        items = items + "\n" + name + " - " + count + "шт." + price;
        totalPrice = totalPrice + count * price;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }

}
