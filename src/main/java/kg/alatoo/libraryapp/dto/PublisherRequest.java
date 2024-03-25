package kg.alatoo.libraryapp.dto;

import jakarta.persistence.OneToMany;
import kg.alatoo.libraryapp.entities.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PublisherRequest {
    private String name;
    private String email;
}
