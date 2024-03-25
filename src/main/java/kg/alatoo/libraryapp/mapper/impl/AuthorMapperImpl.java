package kg.alatoo.libraryapp.mapper.impl;

import kg.alatoo.libraryapp.dto.AuthorResponse;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.mapper.AuthorMapper;
import kg.alatoo.libraryapp.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorMapperImpl implements AuthorMapper {
    private final BookMapper bookMapper;
    @Override
    public List<AuthorResponse> toDtoS(List<Author> all) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        for (Author author: all)
            authorResponses.add(toDto(author));
        return authorResponses;
    }

    private AuthorResponse toDto(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setBookResponses(bookMapper.toDtoS(author.getBooks()));
        authorResponse.setId(author.getId());
        authorResponse.setFullName(author.getFullName());
        return authorResponse;
    }
}
