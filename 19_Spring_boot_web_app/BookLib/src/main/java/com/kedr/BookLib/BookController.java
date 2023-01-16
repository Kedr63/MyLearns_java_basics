package com.kedr.BookLib;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kedr.BookLib.model.Book;
import com.kedr.BookLib.model.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController
{
    @Autowired
    private BookRepository bookRepository;

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

    @GetMapping("/form")
    public String fillForm(Model model)
    {
        Book book = new Book();
        model.addAttribute("book", book);
        return "form";
    }

    @GetMapping("/form-delete")
    public String deleteForm(Model model)
    {
        Book book = new Book();
        model.addAttribute("book", book);
        return "form-delete";
    }

    @GetMapping("/books")
    public @ResponseBody List<Book> list(Model model)
    {
        Iterable<Book> bookIterable = bookRepository.findAll();
        ArrayList<Book> books = new ArrayList<>();
        for(Book book : bookIterable) {
            books.add(book);
        }
        model.addAttribute("books", books);
        model.addAttribute("booksCount", books.size());
        return books;
    }

    /*@PostMapping("/books/")
    public String add(@RequestBody Book book, Model model)  // Вернет в ответе книгу в JSON, если бы написал int и в return return book.getId() -> то
    // в ответе был бы id
    {
        bookRepository.save(book);
        model.addAttribute("book", book);
       return "index/books/";
    }*/

   /* @PostMapping(value = "/books",
    consumes = (MediaType.APPLICATION_JSON_VALUE))*/
    @PostMapping("/books")
    public String add(Book book, Model model)  // Вернет в ответе книгу в JSON, если бы написал int и в return return book.getId() -> то
    // в ответе был бы id
    {
        model.addAttribute("book", book);
        bookRepository.save(book);
        index(model);
        return "form";
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable int id){

       Optional<Book> optionalBook = bookRepository.findById(id);
       if (optionalBook.isPresent()){
           bookRepository.delete(optionalBook.get());
           return new ResponseEntity<>(HttpStatus.OK);
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}