package com.qfedu.storage;

import com.qfedu.service.demo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author XZG
 * @creatTime 2022-03-29
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/")
    @ResponseBody
    public Book getBookById(Integer id){
        Book book = new Book();
        book.setId(id);
        return book;
    }

    //这里没有加 @RequestBody 注解，所以参数是 key-value 形式的
    @PostMapping("/")
    @ResponseBody
    public void addBook(Book book){
        System.out.println("book = " + book);
    }

    @PostMapping("/add")
    public String addBook2(@RequestBody Book book){
        return "redirect:/index";
    }

    @PutMapping("/")
    @ResponseBody
    public void updateBook(@RequestBody Book book){
        System.out.println("book = " + book);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteBookById(@PathVariable Integer id){
        Book book = new Book();
        book.setId(id);
        System.out.println("book = " + book);
    }
}
