package main;

import java.util.ArrayList;
import main.model.Book;
import main.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    private final BookRepository bookRepository;

    @Value("${someParameter.value}")
    private Integer someParameter;

    public DefaultController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Book> bookIterable = bookRepository.findAll();
        ArrayList<Book> books = new ArrayList<>();

        for (Book book : bookIterable) {
            books.add(book);
        }

        model.addAttribute("books", books)
            .addAttribute("booksCount", books.size())
            .addAttribute("someParameter", someParameter);

        return "index";
    }
}