package inheritance_NailAlishev;

import inheritance_NailAlishev.Animal;

public class Dog extends Animal { // Теперь Dog является животным, т.к. он унаследовался от этого класса
    public void bark(){
        System.out.println("Dog is barking..");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating...");
    }
}
