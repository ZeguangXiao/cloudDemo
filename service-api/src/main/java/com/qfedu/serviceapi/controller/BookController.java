package com.qfedu.serviceapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Book;

/**
 * @author XZG
 * @creatTime 2022-03-28
 */
public interface BookController {

    @GetMapping("/")
    @ResponseBody
    Book getBookById(Integer id);
}
