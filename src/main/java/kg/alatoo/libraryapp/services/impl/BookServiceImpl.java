package kg.alatoo.libraryapp.services.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import kg.alatoo.libraryapp.dto.BookRequest;
import kg.alatoo.libraryapp.dto.BookResponse;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.entities.Book;
import kg.alatoo.libraryapp.entities.Publisher;
import kg.alatoo.libraryapp.exception.NotFoundException;
import kg.alatoo.libraryapp.mapper.BookMapper;
import kg.alatoo.libraryapp.repositories.AuthorRepository;
import kg.alatoo.libraryapp.repositories.BookRepository;
import kg.alatoo.libraryapp.repositories.PublisherRepository;
import kg.alatoo.libraryapp.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    @Override
    public void create(BookRequest request) {
        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        Optional<Publisher> publisherOptional = publisherRepository.findByEmail(request.getPublisherEmail());
        if (publisherOptional.isEmpty())
            throw new NotFoundException("no publisher with email: "+ request.getPublisherEmail(), HttpStatus.NOT_FOUND);
        book.setPublisher(publisherOptional.get());
        book.setAuthors(setAuthors(request.getAuthorFullNames()));
        bookRepository.save(book);
    }

    @Override
    public BookResponse getById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty())
            throw new NotFoundException("no book with id: "+ bookId, HttpStatus.NOT_FOUND);
        return bookMapper.toDto(book.get());
    }

    @Override
    public List<BookResponse> getAll() {
        return bookMapper.toDtoS(bookRepository.findAll());
    }

    private List<Author> setAuthors(List<String> authorFullNames) {
        List<Author> authors = new ArrayList<>();
        for (String authorFullName: authorFullNames){
            Optional<Author> authorOptional = authorRepository.findByFullName(authorFullName);
            if (authorOptional.isEmpty())
                throw new NotFoundException("no author with full name: "+ authorFullName, HttpStatus.NOT_FOUND);
            authors.add(authorOptional.get());
        }
        return authors;
    }
}
