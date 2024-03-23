package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService{
    private final AuthorRepository authorRepository;
    @Override
    public void add(String fullName) {
        if (authorRepository.findByFullName(fullName).isEmpty()){
            Author author = new Author();
            author.setFullName(fullName);
            authorRepository.save(author);
        }
    }

    @Override
    public void delete(String fullName) {
        authorRepository.deleteByFullName(fullName);

    }
}
