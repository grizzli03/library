package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.dto.BookDTO;
import kg.alatoo.libraryapp.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class    BookApiController {

    private final BookService bookService;


    @PostMapping("/api/v1/book")
    public void createBook(@Validated @RequestBody BookDTO bookDTO) {
         bookService.create(bookDTO);
    }





}
