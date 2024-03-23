package kg.alatoo.libraryapp.mapper;

import kg.alatoo.libraryapp.dto.BookDTO;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapperImpl implements BookMapper{

    @Override
    public BookDTO bookToBookDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setPublisherName(book.getPublisher()!=null? book.getPublisher().getName(): null);
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setEdition(book.getEdition());
        bookDTO.setAuthors(getAuthroNames(book.getAuthors()));

        return bookDTO;
    }

    private List<String> getAuthroNames(List<Author> authors) {
        List<String> names = new ArrayList<>();
        for (Author author: authors)
            names.add(author.getFullName());
        return names;

    }
}
