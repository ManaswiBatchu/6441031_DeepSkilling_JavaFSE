package com.example;

import com.example.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the bookService bean
        BookService bookService = context.getBean("bookService", BookService.class);

        // Call a method to verify DI works
        bookService.addBook();
    }
}
