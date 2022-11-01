package com.boot.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private static List<Book> list = new ArrayList<>();
    static {
        list.add(new Book(1,"VS","Raman",120));
        list.add(new Book(2,"Bharat","Prafool",110));
        list.add(new Book(3,"Pata Nahi","Mera Liya",140));
        list.add(new Book(4,"Mathura","Krishna",150));
        list.add(new Book(5,"Bhoogol","Aryabhatt",100));
        list.add(new Book(6,"Noble","Oswal",180));
    }
    //get all books
    public List<Book> getListOfBooks(){
        return list;
    }
    //get books by id
    public Book getBookById(int id){
        logger.info("Logger intialized to getBookById ");
        Book book = null;
        try{
       book = list.stream().filter(e->e.getId()==id).findAny().orElse(null);}catch (NoSuchElementException e){
            logger.error("Got an Error: ",e);
            logger.info(" ID {} not found!!!",id);
        }
        return book;
    }
    //get books by name
    public Book getBookByTitle(String title){
        Book book = null;
        try {
            book = list.stream().filter(e -> e.getTitle().equals(title)).findAny().orElse(null);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return book;
    }
    //adding the book
    public Book addBook(Book book){
        list.add(book);
        return book;
    }
    //delete data
    public boolean deleteBook(int id) {
        list = list.stream().filter(book->{
            if(book.getId() != id){
                return true;
            }else {
                return false;
            }
        }).collect(Collectors.toList());

        //in sab ki jagaha
        //list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());
        return false;
    }

    public void updateBook(Book book, int id) {
       list =  list.stream().map(b -> {
            if(b.getId() == id) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
            }
                return b;
        }).collect(Collectors.toList());
    }
}
