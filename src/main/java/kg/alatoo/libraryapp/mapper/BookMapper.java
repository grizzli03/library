package kg.alatoo.libraryapp.mapper;

import kg.alatoo.libraryapp.dto.BookResponse;
import kg.alatoo.libraryapp.entities.Book;

import java.util.List;
import java.util.Set;

public interface BookMapper {
    BookResponse toDto(Book book);

    List<BookResponse> toDtoS(List<Book> books);
}
