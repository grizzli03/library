package kg.alatoo.libraryapp.mapper.impl;

import kg.alatoo.libraryapp.dto.BookResponse;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.entities.Book;
import kg.alatoo.libraryapp.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookResponse toDto(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setPublisherEmail(book.getPublisher()!= null? book.getPublisher().getEmail(): null);
        bookResponse.setAuthorFullNames(book.getAuthors()!=null?book.getAuthors().stream().map(Author::getFullName).collect(Collectors.toList()):  null);

        return bookResponse;
    }

    @Override
    public List<BookResponse> toDtoS(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book: books)
            bookResponses.add(toDto(book));
        return bookResponses;
    }
}
