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
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBooksByID(@PathVariable("id") int id){
        Book book = service.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(book);
        return ResponseEntity.of(Optional.of(book)) ;
    }
   //get single book by name controller
    @GetMapping("/books/{title}")
    public ResponseEntity<Book> getBooksByTitles(@PathVariable("title") String title){
        Book book = service.getBookByTitle(title);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println(service.getBookByTitle(title));
        return ResponseEntity.of(Optional.of(book));
    }
    //add or create book handler
    @PostMapping("/bookRead")
    public ResponseEntity<Book> addBooks(@RequestBody Book book){
       Book book1;
       try {
           book1 = this.service.addBook(book);
           System.out.println(book1);
           return ResponseEntity.of(Optional.of(book));
       }catch (NullPointerException | UnsupportedOperationException | IllegalArgumentException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
    //delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        try{
            boolean i = this.service.deleteBook(id);
            if(i){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //update book handler
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id){
        try {
            this.service.updateBook(book,id);
            System.out.println(book);
            return ResponseEntity.ok().body(book);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
