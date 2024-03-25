package kg.alatoo.libraryapp.services;

import kg.alatoo.libraryapp.dto.AuthorResponse;

import java.util.List;

public interface AuthorService {
    void add(String fullName);

    void delete(String fullName);

    List<AuthorResponse> getAll();
}
