package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.BookRequest;
import kg.alatoo.libraryapp.dto.BookResponse;

import java.util.List;

public interface BookService {
    void create(BookRequest request);

    BookResponse getById(Long bookId);

    List<BookResponse> getAll();
}
