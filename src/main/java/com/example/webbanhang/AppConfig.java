/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.webbanhang;

/**
 *
 * @author Admin
 */
import com.example.webbanhang.models.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<Book> getBooks()
    {
        List<Book> listBooks = new ArrayList<>();
        Book book1 = Book.builder()
                .id(1)
                .name("CN")
                .price(45.0)
                .image("hình1.jpg")
                .build();
        listBooks.add(book1);
        Book book2 = Book.builder()
                .id(2)
                .name("VN")
                .price(35.0)
                .image("hình2.jpg")
                .build();
        listBooks.add(book2);

        return listBooks;
    }
}