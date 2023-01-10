package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  /* Класс Main обязательно создаем в package main (иначе не будет работать если расместить просто в папке java) */

  /** Классы, которые отвечают за то, чтобы выдавать информацию в ответ на запрос (через браузер или
   * какой то еще запрос) называются контроллеры. Создадим такой класс DefaultController.
   * Контроллеры работают по принципу: получают запрос -> отправляют ответ  */

  /* Создадим файл с настройками для подключения к БД /application.properties/ в корне проекта  */

}
