public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /*// было сначала
    // public String toString() {   // перепишем метод toString чтоб выводилась нужная для нас информация
    //    return name + " - " + price;
    // }*/

    public String toString() {
        return name.concat(" - ").concat(Integer.toString(price));   // т.к. /price/ - int (примитив),
        // превратим его в строку
    }
}
