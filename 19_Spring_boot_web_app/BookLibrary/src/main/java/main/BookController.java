package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import main.model.Book;

@RestController
public class BookController
{
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/")
    public List<Book> list()
    {
        Iterable<Book> bookIterable = bookRepository.findAll();
        ArrayList<Book> books = new ArrayList<>();
        for(Book book : bookIterable) {
            books.add(book);
        }
        return books;
    }

    @PostMapping("/books/")
    public Book add(@RequestBody Book book)  // Вернет в ответе книгу в JSON, если бы написал int и в return return book.getId() -> то
    // в ответе был бы id
    {
        bookRepository.save(book);
       return book;
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