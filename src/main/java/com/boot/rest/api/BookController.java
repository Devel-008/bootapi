package com.boot.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService service;

    //get all book handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> bookList = this.service.getListOfBooks();
        if(bookList.size() <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(service.getListOfBooks());
        return ResponseEntity.of(Optional.of(bookList)) ;
    }
    //get single book handler
    @GetMapping("/books/{id}")
    public Book getBooksByID(@PathVariable("id") int id){
        System.out.println(service.getBookById(2));
        return this.service.getBookById(2);
    }
   //get single book by name controller
    @GetMapping("/books/{title}")
    public Book getBooksByTitles(@PathVariable("title") String title){
        System.out.println(service.getBookByTitle(title));
        return this.service.getBookByTitle(title);
    }
    //add or create book handler
    @PostMapping("/bookRead")
    public Book AddBooks(@RequestBody Book book){
       Book book1 = this.service.addBook(book);
        System.out.println(book1);
        return book1;
    }
    //delete book handler
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") int id){
         this.service.deleteBook(id);
    }
    //update book handler
    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable("id") int id){
        this.service.updateBook(book,id);
        System.out.println(book);
        return book;
    }
}
