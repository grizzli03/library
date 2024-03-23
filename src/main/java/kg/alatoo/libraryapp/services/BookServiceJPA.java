package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.BookDTO;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.entities.Book;
import kg.alatoo.libraryapp.entities.Publisher;
import kg.alatoo.libraryapp.mapper.BookMapper;
import kg.alatoo.libraryapp.repositories.AuthorRepository;
import kg.alatoo.libraryapp.repositories.BookRepository;
import kg.alatoo.libraryapp.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceJPA implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

//    public BookDTO testMapper(Book book) {
//        return bookMapper.bookToBookDto(book);
//    }
//
//    @Override
//    public Optional<BookDTO> getBookById(Long id) {
//        return Optional.ofNullable(
//                bookMapper.bookToBookDto(
//                        bookRepository.findById(id)
//                                .orElse(null)
//                )
//        );
//    }

    @Override
    public void create(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setEdition(bookDTO.getEdition());
        book.setAuthors(getAuthors(bookDTO));
        book.setPublisher(getPublisher(bookDTO.getPublisherName()));
        bookRepository.save(book);
    }

    @Override
    public BookDTO getById(Long bookId) {
        if (bookRepository.findById(bookId).isPresent())
            return bookMapper.bookToBookDto(bookRepository.findById(bookId).get());
        return null;
    }

    private Publisher getPublisher(String publisherName) {
        Optional<Publisher> publisherOptional = publisherRepository.findByName(publisherName);
        if (publisherOptional.isEmpty()) {
            System.out.println("not found author with full name: " + publisherName);
            return null;
        }
        else {
            return publisherOptional.get();
        }
    }

    private List<Author> getAuthors(BookDTO bookDTO) {
        List<Author> authors = new ArrayList<>();
        for (String authorFullName:  bookDTO.getAuthors()){
            Optional<Author> authorOptional = authorRepository.findByFullName(authorFullName);
            if (authorOptional.isEmpty())
                System.out.println("not found author with full name: " + authorFullName);
            else {
                authors.add(authorOptional.get());
            }
        }
        return authors;
    }
}
