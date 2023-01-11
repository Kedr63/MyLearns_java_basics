package main;

import java.util.ArrayList;
import java.util.Date;
import main.model.Book;
import main.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
public class Controller {

  @Autowired
  BookRepository bookRepository;

  @RequestMapping("/")
  public String index(Model model) {
   Iterable<Book> bookIterable = bookRepository.findAll();
    ArrayList<Book> books = new ArrayList<>();
    for (Book book : bookIterable){
      books.add(book);
    }
    model.addAttribute("books", books); // теперь этот model будет доступен в index.html
    model.addAttribute("booksCount", books.size());
    return "index";
  }

  /**  @RequestMapping - это путь к тому запросу, по которому должен вызываться этот метод. Здесь метод
   *  будет вызываться в корне сайта. Если в браузере будем запрашивать первую страницу нашего
   *  сайта: http://localhost:8080/
   *  то будет вызываться этот метод и будет печататься дата текущая в браузере по ссылке http://localhost:8080 */

  /* Model - это специальный класс, в котором можно хранить переменные которые потом можно передать в шаблон .html */

}
