package kg.alatoo.libraryapp.services.impl;

import kg.alatoo.libraryapp.dto.AuthorResponse;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.mapper.AuthorMapper;
import kg.alatoo.libraryapp.repositories.AuthorRepository;
import kg.alatoo.libraryapp.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
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

    @Override
    public List<AuthorResponse> getAll() {
        return authorMapper.toDtoS(authorRepository.findAll());
    }
}
