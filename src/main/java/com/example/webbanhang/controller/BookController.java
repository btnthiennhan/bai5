package com.example.webbanhang.controller;

import com.example.webbanhang.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private List<Book> listBook;

    @GetMapping("/")
    public String showAllBooks(Model model) {
        model.addAttribute("listBook", listBook);
        model.addAttribute("title", "Danh sách cuốn sách");
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                // Lưu file vào thư mục static/images
                Path path = Paths.get("src", "main", "resources", "static", "images", fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                book.setImage(fileName); // Lưu tên file vào đối tượng Book
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listBook.add(book); // Thêm sách vào danh sách
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        Optional<Book> editBook = listBook.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
        if (editBook.isPresent()) {
            model.addAttribute("book", editBook.get());
            return "book/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updateBook, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                // Lưu file vào thư mục static/images
                Path path = Paths.get("src", "main", "resources", "static", "images", fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                updateBook.setImage(fileName); // Cập nhật tên file mới vào đối tượng Book
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Cập nhật thông tin sách trong danh sách
        listBook.stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    book.setName(updateBook.getName());
                    book.setPrice(updateBook.getPrice());
                    book.setImage(updateBook.getImage()); // Cập nhật lại tên file ảnh trong danh sách
                });
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        listBook.removeIf(book -> book.getId() == id);
        return "redirect:/";
    }
}
