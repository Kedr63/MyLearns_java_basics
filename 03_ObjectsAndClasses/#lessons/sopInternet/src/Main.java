public class Main {
    public static void main(String[] args) {
        Basket vasyaBasket = new Basket();
        vasyaBasket.add("Молоко", 80);
        vasyaBasket.add("Хлеб", 50);

        Basket petyaBasket = new Basket(500);
        petyaBasket.add("Лопата", 120);
        petyaBasket.add("Бочка", 2900);

        Basket mashaBasket=new Basket("Стол", 5000);

        vasyaBasket.print("Корзина Васи:");
        petyaBasket.print("Корзина Пети:");
        mashaBasket.print("Корзина Маши:");
    }
}
