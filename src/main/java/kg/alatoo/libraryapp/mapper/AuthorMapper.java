package kg.alatoo.libraryapp.mapper;

import kg.alatoo.libraryapp.dto.AuthorResponse;
import kg.alatoo.libraryapp.entities.Author;

import java.util.List;

public interface AuthorMapper {
    List<AuthorResponse> toDtoS(List<Author> all);
}
