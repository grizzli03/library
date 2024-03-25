package kg.alatoo.libraryapp.controllers;

import kg.alatoo.libraryapp.dto.BookRequest;
import kg.alatoo.libraryapp.dto.BookResponse;
import kg.alatoo.libraryapp.entities.Book;
import kg.alatoo.libraryapp.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class    BookApiController {

    private final BookService bookService;


    @PostMapping("/api/v1/book")
    public void createBook(@RequestBody BookRequest request) {
         bookService.create(request);
    }

    @GetMapping("/getById/{bookId}")
    public BookResponse getById(@PathVariable Long bookId){
        return bookService.getById(bookId);
    }

    @GetMapping("/all")
    public List<BookResponse> all(){
        return bookService.getAll();
    }





}
