package kg.alatoo.libraryapp.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.alatoo.libraryapp.entities.Author;
import kg.alatoo.libraryapp.entities.Publisher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookResponse {
    private Long id;

    private String title;

    private String isbn;

    private String publisherEmail;

    private List<String> authorFullNames;
}
